package by.it_academy.jd2.audit_service.core.converters.api;

import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;

public interface IAuditConverter {

    AuditDTO convertAuditEntityToDTO(AuditEntity entity);
}
