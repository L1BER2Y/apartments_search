package by.it_academy.jd2.audit_service.core.converters.api;

import by.it_academy.jd2.audit_service.core.dto.AuditInfoDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;

public interface IAuditConverter {

    AuditInfoDTO convertAuditEntityToDTO(AuditEntity entity);
}
