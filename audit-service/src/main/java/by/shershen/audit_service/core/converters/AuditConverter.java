package by.shershen.audit_service.core.converters;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.UserAuditDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Optional;

@Component
public class AuditConverter implements IAuditConverter {

    @Override
    public AuditDTO convertAuditEntityToAuditDTO(AuditEntity auditEntity) {
        return AuditDTO.builder()
                .uuid(auditEntity.getUuid())
                .dtCreate(auditEntity.getDtCreate())
                .userAuditDTO(new UserAuditDTO(auditEntity.getUuid(), auditEntity.getMail(), auditEntity.getFio(), auditEntity.getRole()))
                .action(auditEntity.getText())
                .type(auditEntity.getEssenceType())
                .typeId(auditEntity.getEssenceId())
                .build();
    }

    @Override
    public AuditInfoDTO convertAuditEntityToInfoDTO(AuditEntity entity) {
        AuditInfoDTO auditInfoDTO = new AuditInfoDTO();
        auditInfoDTO.setUuid(entity.getId());
        auditInfoDTO.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        auditInfoDTO.setUserAuditDTO(new UserAuditDTO(entity.getUuid(), entity.getMail(), entity.getFio(), entity.getRole()));
        auditInfoDTO.setAction(entity.getText());
        auditInfoDTO.setType(entity.getEssenceType());
        auditInfoDTO.setTypeId(entity.getEssenceId());
        return auditInfoDTO;
    }

    @Override
    public AuditEntity convertAuditDTOToAuditEntity(AuditDTO dto) {
        return AuditEntity.builder()
                .id(dto.getUserAuditDTO().getUuid())
                .dtCreate(dto.getDtCreate())
                .uuid(dto.getUuid())
                .mail(dto.getUserAuditDTO().getMail())
                .fio(dto.getUserAuditDTO().getFio())
                .role(dto.getUserAuditDTO().getRole())
                .text(dto.getAction())
                .essenceType(dto.getType())
                .essenceId(dto.getTypeId())
                .build();
    }

    @Override
    public AuditEntity convertAuditOptionalToEntity(Optional<AuditEntity> entity) {
        return entity.orElse(null);
    }
}
