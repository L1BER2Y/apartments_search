package by.shershen.audit_service.core.converters;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.UserAuditDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class AuditConverter implements IAuditConverter {

    @Override
    public AuditInfoDTO convertAuditEntityToDTO(AuditEntity entity) {
        AuditInfoDTO auditInfoDTO = new AuditInfoDTO();
        auditInfoDTO.setUuid(entity.getId());
        auditInfoDTO.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        auditInfoDTO.setUserAuditDTO(new UserAuditDTO(entity.getUuid(), entity.getMail(), entity.getFio(), entity.getRole()));
        auditInfoDTO.setAction(entity.getText());
        auditInfoDTO.setType(entity.getEssenceType());
        auditInfoDTO.setTypeId(entity.getEssenceId());
        return auditInfoDTO;
    }
}
