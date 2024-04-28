package by.shershen.audit_service.core.converters;

import by.shershen.audit_service.core.converters.api.IPageConverter;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.PageOfAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter implements IPageConverter {

    @Override
    public PageOfAuditDTO<AuditInfoDTO> convertPageToPageOfAuditDTO(Page<AuditInfoDTO> page) {
        return new PageOfAuditDTO<AuditInfoDTO>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }
}
