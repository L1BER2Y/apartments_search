package by.shershen.report_service.core.converters;

import by.shershen.report_service.core.converters.api.IPageConverter;
import by.shershen.report_service.core.dto.PageOfReportDTO;
import by.shershen.report_service.core.dto.ReportDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageConverter implements IPageConverter {

    @Override
    public PageOfReportDTO<ReportDTO> convertPageToPageOfReportDTO(Page<ReportDTO> page) {
        return new PageOfReportDTO<ReportDTO>().setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast())
                .setContent(page.getContent());
    }
}
