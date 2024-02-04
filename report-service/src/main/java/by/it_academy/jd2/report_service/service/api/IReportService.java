package by.it_academy.jd2.report_service.service.api;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.UUID;

public interface IReportService {

    void create(Type type, UserActionAuditParamDTO params);

    Page<ReportEntity> getAllReports(Pageable pageable);

    Status getStatusById(String id);

    ResponseEntity<String> save(UUID uuid) throws IOException;
}
