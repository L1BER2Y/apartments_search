package by.it_academy.jd2.audit_service.service.api;

import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface IAuditService {
    Page<AuditDTO> getAudit(Pageable pageable);
    Optional<AuditEntity> getAuditById(UUID id);
    AuditEntity saveAudit(AuditDTO auditDTO);
}
