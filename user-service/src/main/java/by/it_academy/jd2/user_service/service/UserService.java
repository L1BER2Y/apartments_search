package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.aop.Audited;
import by.it_academy.jd2.user_service.core.dto.*;
import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.entity.Status;
import by.it_academy.jd2.user_service.core.exceptions.InternalServerErrorException;
import by.it_academy.jd2.user_service.core.exceptions.ValidationException;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import by.it_academy.jd2.user_service.service.api.IVerificationQueueService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static by.it_academy.jd2.user_service.core.entity.AuditedAction.*;
import static by.it_academy.jd2.user_service.core.entity.EssenceType.USER;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final IVerificationQueueService verificationQueueService;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, ModelMapper modelMapper, IVerificationQueueService verificationQueueService,
                       PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.verificationQueueService = verificationQueueService;
        this.passwordEncoder = passwordEncoder;
    }

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
        return new PageImpl<UserDTO>(dtoList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_USER_BY_ID, essenceType = USER)
    public UserEntity findById(UUID uuid) {
        return convertFromOptionalToEntity(this.userRepository.findById(uuid));
    }

    @Override
    @Transactional
    @Audited(auditedAction = SAVE_USER, essenceType = USER)
    public UserEntity save(UserEntity user) {
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
        UserEntity user = findById(id);
        if(user.getDtUpdate().truncatedTo(ChronoUnit.MILLIS).isEqual(dtUpdate.truncatedTo(ChronoUnit.MILLIS))) {
            throw new ValidationException("Недопустимый dt_update - " + dtUpdate);
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
    public UserEntity find() {
        UserDetailsDTO userDetails = (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findById(userDetails.getId());
        return convertToEntity(userEntity);
    }

    private static UserDTO apply(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setDtCreate(user.getDtCreate());
        userDTO.setDtUpdate(user.getDtUpdate());
        userDTO.setMail(user.getMail());
        userDTO.setFio(user.getFio());
        userDTO.setRole(user.getRole());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }

    private UserEntity convertFromOptionalToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }

    private void validateMail(String mail) {
        if (userRepository.existsByMail(mail)) {
            throw new ValidationException("Такой логин уже используется");
        }
    }
}
