package by.shershen.audit_service.service;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.repository.AuditRepository;
import by.shershen.audit_service.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditService implements IAuditService {
    private final AuditRepository repository;
    private final IAuditConverter auditConverter;

    @Override
    public Page<AuditInfoDTO> getAudit(Pageable pageable) {
        Page<AuditEntity> entityPage = this.repository.findAll(pageable);
        List<AuditInfoDTO> auditInfoDTOList = entityPage.stream()
                .map(auditConverter::convertAuditEntityToInfoDTO)
                .toList();
        return new PageImpl<>(auditInfoDTOList, entityPage.getPageable(), entityPage.getTotalElements());
    }

    @Override
    public AuditInfoDTO getAuditById(UUID id) {
        Optional<AuditEntity> entityOptional = this.repository.findById(id);
        AuditEntity entity = auditConverter.convertAuditOptionalToEntity(entityOptional);
        return auditConverter.convertAuditEntityToInfoDTO(entity);
    }

    @Override
    public AuditEntity saveAudit(AuditDTO auditDTO) {
        AuditEntity entity = auditConverter.convertAuditDTOToAuditEntity(auditDTO);
        return this.repository.save(entity);
    }

}
