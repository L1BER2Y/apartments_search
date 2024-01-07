package by.it_academy.jd2.user_service.service;

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
    public Page<UserEntity> getPage(PageDTO page) {
        return this.userRepository.findAll(PageRequest.of(page.getNumber(), page.getSize()));
    }

    @Override
    public Optional<UserEntity> findById(UUID uuid) {
        return this.userRepository.findById(uuid);
    }

    @Override
    @Transactional
    public void save(UserEntity user) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setUserRole(Role.USER);
        entity.setUserStatus(user.getUserStatus());
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
    public void update(UserEntity entity, UUID id, LocalDateTime dtUpdate) {
        Optional<UserEntity> optional = findById(id);
        UserEntity user = convertToEntity(optional);
        user.setDtUpdate(dtUpdate);
        user.setMail(entity.getMail());
        user.setFio(entity.getFio());
        user.setUserRole(entity.getUserRole());
        user.setUserStatus(entity.getUserStatus());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));

        try {
            this.userRepository.save(user);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public UserEntity find() {
        UserEntity userDetails = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findById(userDetails.getId());
        return convertToEntity(userEntity);
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }
}
