package by.it_academy.jd2.Mk_JD2_103_23.user_service.service.api;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.PageDTO;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface IUserService {
    Page<UserEntity> getPage(PageDTO page);
    UserEntity getById(UUID id);
    UserEntity createUser(UserEntity user);
    void updateUser(UserEntity entity);
}
