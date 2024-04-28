package by.shershen.audit_service.service;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.repository.AuditRepository;
import by.shershen.audit_service.service.api.IAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {
    private final AuditRepository repository;
    private final IAuditConverter auditConverter;
    private final ModelMapper modelMapper;

    public AuditService(AuditRepository repository, IAuditConverter auditConverter, ModelMapper modelMapper) {
        this.repository = repository;
        this.auditConverter = auditConverter;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<AuditInfoDTO> getAudit(Pageable pageable) {
        Page<AuditEntity> entityPage = this.repository.findAll(pageable);
        List<AuditInfoDTO> auditInfoDTOList = entityPage.stream()
                .map(auditConverter::convertAuditEntityToDTO)
                .toList();
        return new PageImpl<AuditInfoDTO>(auditInfoDTOList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    public AuditInfoDTO getAuditById(UUID id) {
        Optional<AuditEntity> entityOptional = this.repository.findById(id);
        AuditEntity entity = convertOptionalToEntity(entityOptional);
        return auditConverter.convertAuditEntityToDTO(entity);
    }

    @Override
    public AuditEntity saveAudit(AuditDTO auditDTO) {
        AuditEntity entity = apply(auditDTO);
        return this.repository.save(entity);
    }

    private static AuditEntity apply(AuditDTO dto) {
        AuditEntity entity = new AuditEntity();
        entity.setId(dto.getUuid());
        entity.setDtCreate(dto.getDtCreate());
        entity.setUuid(dto.getUserAuditDTO().getUuid());
        entity.setMail(dto.getUserAuditDTO().getMail());
        entity.setFio(dto.getUserAuditDTO().getFio());
        entity.setRole(dto.getUserAuditDTO().getRole());
        entity.setText(dto.getAction());
        entity.setEssenceType(dto.getType());
        entity.setEssenceId(dto.getTypeId());
        return entity;
    }
    private AuditEntity convertOptionalToEntity(Optional<AuditEntity> entity) {
        return modelMapper.map(entity, AuditEntity.class);
    }

}
