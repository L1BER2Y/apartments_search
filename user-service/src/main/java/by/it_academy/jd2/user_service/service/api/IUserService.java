package by.it_academy.jd2.user_service.service.api;

import by.it_academy.jd2.user_service.core.dto.UserDTO;
import by.it_academy.jd2.user_service.core.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    UserEntity find();
    Page<UserDTO> getPage(Pageable pageable);
    UserEntity findById(UUID id);
    UserEntity save(UserEntity user);
    UserEntity update(UserEntity entity, UUID id, LocalDateTime dtUpdate);

    UserEntity register(UserEntity entity);
}
