package by.shershen.audit_service.core.converters.api;

import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;

public interface IAuditConverter {

    AuditInfoDTO convertAuditEntityToDTO(AuditEntity entity);
}
