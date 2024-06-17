package by.shershen.audit_service.core.converters.api;

import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;

import java.util.Optional;

public interface IAuditConverter {

    AuditDTO convertAuditEntityToAuditDTO(AuditEntity auditEntity);

    AuditInfoDTO convertAuditEntityToInfoDTO(AuditEntity entity);

    AuditEntity convertAuditDTOToAuditEntity(AuditDTO dto);

    AuditEntity convertAuditOptionalToEntity(Optional<AuditEntity> optional);
}
