package by.it_academy.jd2.report_service.core.converters.api;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.ReportDTO;
import org.springframework.data.domain.Page;

public interface IPageConverter {

    PageOfReportDTO<ReportDTO> convertPageToPageOfReportDTO(Page<ReportDTO> page);
}
