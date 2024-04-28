package by.shershen.audit_service.core.converters.api;

import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.PageOfAuditDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageOfAuditDTO<AuditInfoDTO> convertPageToPageOfAuditDTO(Page<AuditInfoDTO> page);
}
