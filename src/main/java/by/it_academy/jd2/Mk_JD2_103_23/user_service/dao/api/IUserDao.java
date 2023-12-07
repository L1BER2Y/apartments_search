package by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.api;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.Page;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import org.springframework.data.repository.Repository;
import java.util.List;
import java.util.UUID;

public interface IUserDao extends Repository<UserEntity, UUID> {
    List<UserEntity> findAll(Page page);
    void saveUser(UserEntity user);
    UserEntity getAllById(UUID id);


}
