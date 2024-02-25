package by.it_academy.jd2.audit_service.service;

import by.it_academy.jd2.audit_service.core.converters.api.IAuditConverter;
import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import by.it_academy.jd2.audit_service.repository.AuditRepository;
import by.it_academy.jd2.audit_service.service.api.IAuditService;
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

    public AuditService(AuditRepository repository, IAuditConverter auditConverter) {
        this.repository = repository;
        this.auditConverter = auditConverter;
    }

    @Override
    public Page<AuditDTO> getAudit(Pageable pageable) {
        Page<AuditEntity> entityPage = this.repository.findAll(pageable);
        List<AuditDTO> auditDTOList = entityPage.stream()
                .map(auditConverter::convertAuditEntityToDTO)
                .toList();
        return new PageImpl<AuditDTO>(auditDTOList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    public Optional<AuditEntity> getAuditById(UUID id) {
        return this.repository.findById(id);
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
}
