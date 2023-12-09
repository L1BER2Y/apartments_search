package by.it_academy.jd2.Mk_JD2_103_23.user_service.service;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.api.IUserDao;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api.IUserService;

import java.util.List;
import java.util.UUID;

public class UserService implements IUserService {
    private final IUserDao dao;

    public UserService(IUserDao dao) {
        this.dao = dao;
    }
// для перевода из entity в dto применить паттерн адаптер
    @Override
    public List<UserEntity> getAll(PageDTO page) {
        return this.dao.findAll();
    }

    @Override
    public UserEntity getById(UUID uuid) {
        return this.dao.getAllById(uuid);
    }

    @Override
    public void createUser(UserEntity user) {
        this.dao.addUser(user);
    }
}
