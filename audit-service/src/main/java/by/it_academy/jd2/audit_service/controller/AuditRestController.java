package by.it_academy.jd2.audit_service.controller;

import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDTO;
import by.it_academy.jd2.audit_service.core.entity.AuditEntity;
import by.it_academy.jd2.audit_service.service.api.IAuditService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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
    public PageOfAuditDTO getAudit(@RequestParam(defaultValue =  "0") Integer page,
                                   @RequestParam(defaultValue = "20") Integer size
    ) {
        PageOfAuditDTO auditDTO = new PageOfAuditDTO(page, size);
        Page<AuditEntity> audit = this.service.getAudit(auditDTO);
        return new PageOfAuditDTO(audit.getNumber(), audit.getSize(),
                audit.getTotalPages(), audit.getTotalElements(), audit.isFirst(),
                audit.getNumberOfElements(), audit.isLast(), audit.getContent());
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public AuditDTO getAuditById(@PathVariable UUID uuid) {
        Optional<AuditEntity> auditById = this.service.getAuditById(uuid);
        return convertToDto(auditById);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public AuditDTO acceptReq(@RequestHeader String Authorization,
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
}
