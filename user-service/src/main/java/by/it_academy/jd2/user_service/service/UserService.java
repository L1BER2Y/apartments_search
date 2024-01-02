package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.user_service.core.dto.*;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import by.it_academy.jd2.user_service.service.api.IVerificationQueueService;
import org.modelmapper.ModelMapper;
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
    private final IVerificationQueueService queueService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenHandler jwtTokenHandler;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, IVerificationQueueService queueService, PasswordEncoder passwordEncoder, JwtTokenHandler jwtTokenHandler) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.queueService = queueService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenHandler = jwtTokenHandler;
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
    public void saveUser(UserEntity user) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(LocalDateTime.now());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setUserRole(Role.USER);
        entity.setUserStatus(Status.WAITING_ACTIVATION);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(entity);
        this.queueService.addInVerificationQueue(entity);
    }

    @Override
    public void updateUser(UserEntity entity, UUID id) {
        Optional<UserEntity> optional = findById(id);
        UserEntity user = convertToEntity(optional);
        user.setDtUpdate(entity.getDtUpdate());
        user.setMail(entity.getMail());
        user.setFio(entity.getFio());
        user.setUserRole(entity.getUserRole());
        user.setUserStatus(entity.getUserStatus());
        user.setPassword(passwordEncoder.encode(entity.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public UserEntity findInfo() {
        UserEntity userDetails = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<UserEntity> userEntity = userRepository.findById(userDetails.getId());
        return convertToEntity(userEntity);
    }

    @Override
    public String login(UserLoginDTO user) {
        Optional<UserEntity> optional = userRepository.findByMail(user.getMail());
        UserEntity userEntity = convertToEntity(optional);
        return jwtTokenHandler.generateAccessToken(user);
    }

    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }
}
