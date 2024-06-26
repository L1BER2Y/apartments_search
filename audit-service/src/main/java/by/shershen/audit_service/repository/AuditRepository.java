package by.shershen.audit_service.repository;

import by.shershen.audit_service.core.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, UUID> {
    List<AuditEntity> findAll();
    Optional<AuditEntity> findById(UUID uuid);
}
