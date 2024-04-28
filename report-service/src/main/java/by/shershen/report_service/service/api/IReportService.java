package by.shershen.report_service.service.api;

import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.Status;
import by.shershen.report_service.core.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.UUID;

public interface IReportService {

    void create(Type type, UserActionAuditParamDTO params);

    Page<ReportDTO> getAllReports(Pageable pageable);

    Status getStatusById(String id);

    ResponseEntity<String> save(UUID uuid) throws IOException;
}
