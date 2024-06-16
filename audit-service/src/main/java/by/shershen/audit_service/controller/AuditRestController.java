package by.shershen.audit_service.controller;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.converters.api.IPageConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.PageOfAuditDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.service.api.IAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/audit")
public class AuditRestController {
    private final IAuditService service;
    private final IPageConverter pageConverter;
    private final IAuditConverter auditConverter;

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
    public AuditDTO acceptSaveRequest(@RequestHeader("Authorization") String AUTHORIZATION,
                                      @RequestBody AuditDTO auditDTO){
        AuditEntity saveAudit = this.service.saveAudit(auditDTO);
        return auditConverter.convertAuditEntityToAuditDTO(saveAudit);
    }
}
