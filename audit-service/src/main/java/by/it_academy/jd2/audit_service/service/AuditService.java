package by.it_academy.jd2.audit_service.service;

import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import by.it_academy.jd2.audit_service.repository.AuditRepository;
import by.it_academy.jd2.audit_service.service.api.IAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {
    private final AuditRepository repository;

    public AuditService(AuditRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<AuditEntity> getAudit(PageOfAuditDTO page) {
        return this.repository.findAll(PageRequest.of(page.getNumber(), page.getSize()));
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
