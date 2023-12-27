package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.core.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    Page<UserEntity> getPage(PageDTO page);
    Optional<UserEntity> findById(UUID id);
    void saveUser(UserEntity user);
    void updateUser(UserEntity entity, UUID id);
}
