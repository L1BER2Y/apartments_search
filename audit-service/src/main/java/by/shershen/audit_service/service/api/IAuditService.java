package by.shershen.audit_service.service.api;

import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAuditService {
    Page<AuditInfoDTO> getAudit(Pageable pageable);
    AuditInfoDTO getAuditById(UUID id);
    AuditEntity saveAudit(AuditDTO auditDTO);
}
