package by.shershen.user_service.repository;

import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByMail(String mail);

    @Query("SELECT new by.shershen.user_service.core.dto.UserDetailsDTO(u.id, u.mail, u.fio, u.role) FROM UserEntity AS u WHERE u.mail = :email")
    Optional<UserDetailsDTO> findIdFioAndRoleByEmail(String email);

    Boolean existsByMail(String mail);
}
