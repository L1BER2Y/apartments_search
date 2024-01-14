package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.entity.UserEntity;
import by.it_academy.jd2.user_service.core.dto.PageDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {
    UserEntity find();
    Page<UserEntity> getPage(PageDTO page);
    Optional<UserEntity> findById(UUID id);
    void save(UserEntity user);
    void update(UserEntity entity, UUID id, LocalDateTime dtUpdate);

    void register(UserEntity entity);
}
