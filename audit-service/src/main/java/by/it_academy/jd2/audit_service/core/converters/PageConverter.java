package by.it_academy.jd2.audit_service.core.converters;

import by.it_academy.jd2.audit_service.core.converters.api.IPageConverter;
import by.it_academy.jd2.audit_service.core.dto.AuditDTO;
import by.it_academy.jd2.audit_service.core.dto.PageOfAuditDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter implements IPageConverter {

    @Override
    public PageOfAuditDTO<AuditDTO> convertPageToPageOfAuditDTO(Page<AuditDTO> page) {
        return new PageOfAuditDTO<AuditDTO>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }
}
