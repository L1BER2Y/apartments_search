package by.shershen.user_service.service;

import by.shershen.user_service.aop.Audited;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.exceptions.EntityNotFoundException;
import by.shershen.user_service.core.exceptions.InternalServerErrorException;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.service.api.IUserService;
import by.shershen.user_service.service.api.IVerificationQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.shershen.user_service.core.entity.AuditedAction.*;
import static by.shershen.user_service.core.entity.EssenceType.USER;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final IUserConverter userConverter;
    private final IVerificationQueueService verificationQueueService;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    @Audited(auditedAction = REGISTRATION, essenceType = USER)
    public UserEntity register(UserEntity user) {
        validateMail(user.getMail());
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setRole(Role.USER);
        entity.setStatus(Status.WAITING_ACTIVATION);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            this.userRepository.saveAndFlush(entity);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        this.verificationQueueService.add(entity);
        return entity;
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_ALL_USERS, essenceType = USER)
    public Page<UserDTO> getPage(Pageable pageable) {
        Page<UserEntity> entityPage = this.userRepository.findAll(pageable);
        List<UserDTO> dtoList = entityPage.stream()
                .map(UserService::apply)
                .toList();
        return new PageImpl<>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_USER_BY_ID, essenceType = USER)
    public UserDTO findById(UUID uuid) {
        UserEntity entity = userConverter.convertFromOptionalToEntity(this.userRepository.findById(uuid));
        if (entity == null) {
            throw new EntityNotFoundException("No user found by this id");
        }
        return userConverter.convertFromEntityToDTO(entity);
    }

    @Override
    @Transactional
    @Audited(auditedAction = SAVE_USER, essenceType = USER)
    public UserEntity save(UserEntity user) {
        validateMail(user.getMail());
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setRole(user.getRole());
        entity.setStatus(user.getStatus());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            this.userRepository.saveAndFlush(entity);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return entity;
    }

    @Override
    @Transactional
    @Audited(auditedAction = UPDATE_USER, essenceType = USER)
    public UserEntity update(UserEntity entity, UUID id, LocalDateTime dtUpdate) {
        Optional<UserEntity> optional = this.userRepository.findById(id);
        UserEntity user = userConverter.convertFromOptionalToEntity(optional);
        if(user.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).isEqual(dtUpdate.truncatedTo(ChronoUnit.MILLIS))) {
            throw new ValidationException("Unacceptable dt_update - " + dtUpdate);
        } else {
            user.setDtUpdate(dtUpdate);
            user.setMail(entity.getMail());
            user.setFio(entity.getFio());
            user.setRole(entity.getRole());
            user.setStatus(entity.getStatus());
            user.setPassword(passwordEncoder.encode(entity.getPassword()));
        }
        try {
            this.userRepository.saveAndFlush(user);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
        return user;
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_ME, essenceType = USER)
    public UserDTO find() {
        UserDetailsDTO userDetails = (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findById(userDetails.getId());
        UserEntity entity = userConverter.convertFromOptionalToEntity(userEntity);
        return userConverter.convertFromEntityToDTO(entity);
    }

    private static UserDTO apply(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setDtCreate(user.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setDtUpdate(user.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        userDTO.setMail(user.getMail());
        userDTO.setFio(user.getFio());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

    private void validateMail(String mail) {
        if (userRepository.existsByMail(mail)) {
            throw new ValidationException("This login has already been used");
        }
    }
}
