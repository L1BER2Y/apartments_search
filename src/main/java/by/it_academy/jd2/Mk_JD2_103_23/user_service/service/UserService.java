package by.it_academy.jd2.Mk_JD2_103_23.user_service.service;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserBuilder;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.UserDTO;
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
    public List<UserDTO> getAll(PageDTO page) {
        return this.dao.findAll()
                .stream().map(UserService::map).toList();
    }

    @Override
    public UserDTO getById(UUID id) {
        return null;
    }

    @Override
    public void createUser(UserDTO user) {

    }

    private static UserDTO map(UserEntity entity) {
        return new UserBuilder()
                .setId(entity.getId())
                .setDt_create(entity.getDt_create())
                .setDt_update(entity.getDt_update())
                .setMail(entity.getMail())
                .setFio(entity.getFio())
                .setUserRole(entity.getUserRole())
                .setUserStatus(entity.getUserStatus())
                .build();
    }
}
