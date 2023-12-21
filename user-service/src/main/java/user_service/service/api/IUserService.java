package user_service.service.api;

import user_service.core.dto.PageDTO;
import user_service.dao.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUserService {
    Page<UserEntity> getPage(PageDTO page);
    UserEntity findById(UUID id);
    UserEntity saveUser(UserEntity user);
    void updateUser(UserEntity entity);
}
