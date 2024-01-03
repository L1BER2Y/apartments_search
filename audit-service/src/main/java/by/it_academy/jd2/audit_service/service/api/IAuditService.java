package by.it_academy.jd2.audit_service.service.api;

import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IAuditService {
    Page<AuditEntity> getAudit(PageOfAuditDTO pageOfAuditDTO);
    Optional<AuditEntity> getAuditById(UUID id);
    AuditEntity saveAction(AuditEntity entity);
}
