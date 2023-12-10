package by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.api;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import java.util.UUID;

public interface IUserDao extends Repository<UserEntity, UUID> {
    Page<UserEntity> findAll(Pageable pageable);
    UserEntity addUser(UserEntity user);
    UserEntity getAllById(UUID id);
    void update(UserEntity entity);
}
