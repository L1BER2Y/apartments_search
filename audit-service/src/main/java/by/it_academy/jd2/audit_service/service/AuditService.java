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
    private final ModelMapper mapper;

    public AuditService(AuditRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    public AuditEntity saveAudit(AuditDTO dto) {
        AuditEntity entity = convertToEntity(dto);
        return this.repository.save(entity);
    }

    private AuditEntity convertToEntity(AuditDTO dto) {
        return mapper.map(dto, AuditEntity.class);
    }
}
