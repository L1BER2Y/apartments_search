package by.it_academy.jd2.user_service.service;

import by.it_academy.jd2.user_service.core.dto.PageDTO;
import by.it_academy.jd2.user_service.core.dto.Role;
import by.it_academy.jd2.user_service.core.dto.Status;
import by.it_academy.jd2.user_service.dao.api.IUserDao;
import by.it_academy.jd2.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.user_service.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final IUserDao dao;

    public UserService(IUserDao dao) {
        this.dao = dao;
    }

    @Override
    public Page<UserEntity> getPage(PageDTO page) {
        return this.dao.findAll(PageRequest.of(page.getNumber(), page.getSize()));
    }

    @Override
    public UserEntity findById(UUID uuid) {
        return this.dao.findById(uuid);
    }

    @Override
    public void saveUser(UserEntity user) {
        UserEntity entity = new UserEntity();
        entity.setMail(user.getMail());
        entity.setFio(user.getFio());
        entity.setPassword(user.getPassword());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(LocalDateTime.now());
        entity.setId(UUID.randomUUID());
        entity.setUserRole(Role.USER);
        entity.setUserStatus(Status.WAITING_ACTIVATION);
        this.dao.save(entity);
    }

    @Override
    public void updateUser(UserEntity entity) {
        this.dao.save(entity);
    }
}
