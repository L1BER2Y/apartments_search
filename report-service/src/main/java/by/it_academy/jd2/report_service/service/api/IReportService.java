package by.it_academy.jd2.report_service.service.api;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.UUID;

public interface IReportService {

    void createReport(Type type, UserActionAuditParamDTO params) throws IOException;

    Page<ReportEntity> getAllReports(PageOfReportDTO reportDTO);

    Status getStatusById(String id);

    ResponseEntity<String> save(UUID uuid) throws IOException;
}
