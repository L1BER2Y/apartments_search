package by.shershen.user_service.repository;

import by.shershen.user_service.core.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationEntity, UUID> {

    Optional<VerificationEntity> findByCodeAndMail(String code, String mail);

    Optional<VerificationEntity> findFirstBySendCodeFalse();
}
