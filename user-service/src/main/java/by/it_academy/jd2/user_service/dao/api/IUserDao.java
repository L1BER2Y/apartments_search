package by.it_academy.jd2.user_service.dao.api;

import by.it_academy.jd2.user_service.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import java.util.UUID;

public interface IUserDao extends Repository<UserEntity, UUID> {
    Page<UserEntity> findAll(Pageable pageable);
    default void save(UserEntity user) {
    }
    UserEntity findById(UUID id);
}
