package by.shershen.report_service.core.converters.api;

import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.entity.ReportEntity;

public interface IReportConverter {

    ReportDTO convertReportEntityToDTO(ReportEntity entity);
}
