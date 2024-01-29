package by.it_academy.jd2.audit_service.controller;

import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDTO;
import by.it_academy.jd2.audit_service.core.dto.UserAuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import by.it_academy.jd2.audit_service.service.api.IAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditRestController {
    private final IAuditService service;
    private final ModelMapper mapper;

    public AuditRestController(IAuditService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseBody
    public Page<AuditDTO> getAudit(@RequestParam(defaultValue =  "0") Integer page,
                                   @RequestParam(defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditEntity> audit = this.service.getAudit(pageable);
        return audit.map(AuditRestController::apply);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public AuditDTO getAuditById(@PathVariable UUID uuid) {
        Optional<AuditEntity> auditById = this.service.getAuditById(uuid);
        return convertToDto(auditById);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuditDTO acceptReq(@RequestHeader("Authorization") String AUTHORIZATION,
                              @RequestBody AuditDTO auditDTO){
        AuditEntity saveAudit = this.service.saveAudit(auditDTO);
        return convertToDto(saveAudit);
    }

    private AuditDTO convertToDto(Optional<AuditEntity> entity) {
        return mapper.map(entity, AuditDTO.class);
    }
    private AuditDTO convertToDto(AuditEntity entity) {
        return mapper.map(entity, AuditDTO.class);
    }

    private static AuditDTO apply(AuditEntity audit) {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUuid(audit.getUuid());
        auditDTO.setDtCreate(audit.getDtCreate());
        auditDTO.setUserAuditDTO(new UserAuditDTO(audit.getUuid(), audit.getMail(), audit.getFio(), audit.getRole()));
        auditDTO.setAction(audit.getText());
        auditDTO.setType(audit.getEssenceType());
        auditDTO.setTypeId(audit.getEssenceId());
        return auditDTO;
    }
}
