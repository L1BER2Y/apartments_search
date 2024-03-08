package by.it_academy.jd2.audit_service.controller;

import by.it_academy.jd2.audit_service.core.converters.api.IPageConverter;
import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.AuditInfoDTO;
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
import java.util.stream.Stream;

@RestController
@RequestMapping("/audit")
public class AuditRestController {
    private final IAuditService service;
    private final ModelMapper mapper;
    private final IPageConverter pageConverter;

    public AuditRestController(IAuditService service, ModelMapper mapper, IPageConverter pageConverter) {
        this.service = service;
        this.mapper = mapper;
        this.pageConverter = pageConverter;
    }

    @GetMapping
    @ResponseBody
    public PageOfAuditDTO<AuditInfoDTO> getAudit(@RequestParam(name = "page", defaultValue =  "1") Integer page,
                                                 @RequestParam(name = "size", defaultValue = "20") Integer size
    ) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<AuditInfoDTO> audit = this.service.getAudit(pageable);
        return pageConverter.convertPageToPageOfAuditDTO(audit);
    }

    @GetMapping("/{uuid}")
    @ResponseBody
    public AuditInfoDTO getAuditById(@PathVariable UUID uuid) {
        return this.service.getAuditById(uuid);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AuditDTO acceptReq(@RequestHeader("Authorization") String AUTHORIZATION,
                              @RequestBody AuditDTO auditDTO){
        AuditEntity saveAudit = this.service.saveAudit(auditDTO);
        return convertToDto(saveAudit);
    }

    private AuditDTO convertToDto(AuditEntity entity) {
        return mapper.map(entity, AuditDTO.class);
    }
}
