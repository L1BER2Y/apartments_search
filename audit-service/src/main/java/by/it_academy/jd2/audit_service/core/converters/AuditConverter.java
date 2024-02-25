package by.it_academy.jd2.audit_service.core.converters;

import by.it_academy.jd2.audit_service.core.converters.api.IAuditConverter;
import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.UserAuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import org.springframework.stereotype.Component;

@Component
public class AuditConverter implements IAuditConverter {

    @Override
    public AuditDTO convertAuditEntityToDTO(AuditEntity entity) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUuid(entity.getId());
        auditDTO.setDtCreate(entity.getDtCreate());
        auditDTO.setUserAuditDTO(new UserAuditDTO(entity.getUuid(), entity.getMail(), entity.getFio(), entity.getRole()));
        auditDTO.setAction(entity.getText());
        auditDTO.setType(entity.getEssenceType());
        auditDTO.setTypeId(entity.getEssenceId());
        return auditDTO;
    }
}
