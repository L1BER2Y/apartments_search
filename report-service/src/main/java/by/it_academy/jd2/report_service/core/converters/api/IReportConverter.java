package by.it_academy.jd2.report_service.core.converters.api;

import by.it_academy.jd2.report_service.core.dto.ReportDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;

public interface IReportConverter {

    ReportDTO convertReportEntityToDTO(ReportEntity entity);
}
