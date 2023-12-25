package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.dao.entity.UserEntity;
import by.it_academy.jd2.user_service.core.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUserService {
    Page<UserEntity> getPage(PageDTO page);
    UserEntity findById(UUID id);
    void saveUser(UserEntity user);
    void updateUser(UserEntity entity);
}
