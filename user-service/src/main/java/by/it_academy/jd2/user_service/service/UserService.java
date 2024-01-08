package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.aop.Audited;
import by.it_academy.jd2.user_service.core.dto.*;
import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.exceptions.InternalServerErrorException;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import by.it_academy.jd2.user_service.service.api.IVerificationQueueService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
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
    @Audited(auditedAction = INFO_ABOUT_ALL_USERS, essenceType = USER)
    public Page<UserEntity> getPage(PageDTO page) {
        return this.userRepository.findAll(PageRequest.of(page.getNumber(), page.getSize()));
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_USER_BY_ID, essenceType = USER)
    public Optional<UserEntity> findById(UUID uuid) {
        return this.userRepository.findById(uuid);
    }

    @Override
    @Transactional
    @Audited(auditedAction = SAVE_USER, essenceType = USER)
    public void save(UserEntity user) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setRole(Role.USER);
        entity.setStatus(user.getStatus());
        entity.setPassword(passwordEncoder.encode(user.getPassword()));

        try{
            this.userRepository.save(entity);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        this.verificationQueueService.add(entity);
    }

    @Override
    @Transactional
    @Audited(auditedAction = UPDATE_USER, essenceType = USER)
    public void update(UserEntity entity, UUID id, LocalDateTime dtUpdate) {
        Optional<UserEntity> optional = findById(id);
        UserEntity user = convertToEntity(optional);
        user.setDtUpdate(dtUpdate);
        user.setMail(entity.getMail());
        user.setFio(entity.getFio());
        user.setRole(entity.getRole());
        user.setStatus(entity.getStatus());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    @Audited(auditedAction = INFO_ABOUT_ME, essenceType = USER)
    public UserEntity find() {
        UserEntity userDetails = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findById(userDetails.getId());
        return convertToEntity(userEntity);
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }
}
