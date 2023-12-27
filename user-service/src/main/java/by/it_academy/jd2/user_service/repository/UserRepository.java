package by.it_academy.jd2.user_service.repository;

import by.it_academy.jd2.user_service.core.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findById(UUID id);
}
