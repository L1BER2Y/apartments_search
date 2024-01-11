package by.it_academy.jd2.report_service.service.api;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IReportService {

    void createReport(Type type, UserActionAuditParamDTO params);

    Page<ReportEntity> getAllReports(PageOfReportDTO reportDTO);

    Status getStatusById(String id);

    String save(UUID uuid);
}
