package by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<UserEntity> getAll(PageDTO page);
    UserEntity getById(UUID id);
    void createUser(UserEntity user);
}
