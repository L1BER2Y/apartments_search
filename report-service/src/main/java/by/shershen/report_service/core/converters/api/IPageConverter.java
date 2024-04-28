package by.shershen.report_service.core.converters.api;

import by.shershen.report_service.core.dto.PageOfReportDTO;
import by.shershen.report_service.core.dto.ReportDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageOfReportDTO<ReportDTO> convertPageToPageOfReportDTO(Page<ReportDTO> page);
}
