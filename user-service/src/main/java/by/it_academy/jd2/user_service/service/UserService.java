package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.core.dto.*;
import by.it_academy.jd2.user_service.repository.UserRepository;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository dao;
    private final ModelMapper modelMapper;

    public UserService(UserRepository dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<UserEntity> getPage(PageDTO page) {
        return this.dao.findAll(PageRequest.of(page.getNumber(), page.getSize()));
    }

    @Override
    public Optional<UserEntity> findById(UUID uuid) {
        return this.dao.findById(uuid);
    }

    @Override
    public void saveUser(UserEntity user) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(LocalDateTime.now());
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setUserRole(Role.USER);
        entity.setUserStatus(Status.WAITING_ACTIVATION);
        entity.setPassword(user.getPassword());
        this.dao.save(entity);
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
        user.setPassword(entity.getPassword());
        this.dao.save(user);
    }
    private UserEntity convertToEntity(Optional<UserEntity> entity) {
        return modelMapper.map(entity, UserEntity.class);
    }
}
