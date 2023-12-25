package by.it_academy.jd2.audit_service.controller;

import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audit")
public class AuditServiceController {

    @GetMapping
    @ResponseBody
    public ResponseEntity<PageOfAuditDto> getAudit(@RequestParam(defaultValue =  "0") Integer page,
                                                   @RequestParam(defaultValue = "20") Integer size
    ) {
        PageOfAuditDto auditDTO = new PageOfAuditDto(page, size);
        return ResponseEntity.ok(auditDTO);
    }
}
