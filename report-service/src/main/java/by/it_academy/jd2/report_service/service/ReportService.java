package by.it_academy.jd2.report_service.service;

import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.AuditEntity;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import by.it_academy.jd2.report_service.repository.AuditRepository;
import by.it_academy.jd2.report_service.repository.ReportRepository;
import by.it_academy.jd2.report_service.service.api.IReportGenerator;
import by.it_academy.jd2.report_service.service.api.IReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import static by.it_academy.jd2.report_service.core.entity.Status.*;

@Slf4j
@Service
public class ReportService implements IReportService {

    private static final String FILE_DIRECTORY = ".";
    private final ReportRepository reportRepository;
    private final AuditRepository auditRepository;
    private final IReportGenerator reportGenerator;

    public ReportService(ReportRepository reportRepository, AuditRepository auditRepository, IReportGenerator reportGenerator) {
        this.reportRepository = reportRepository;
        this.auditRepository = auditRepository;
        this.reportGenerator = reportGenerator;
    }

    @Override
    @Transactional
    public void create(Type type, UserActionAuditParamDTO params) {
        ReportEntity entity = new ReportEntity();
        entity.setId(UUID.randomUUID());
        entity.setDtCreate(LocalDateTime.now());
        entity.setDtUpdate(entity.getDtCreate());
        entity.setStatus(PROGRESS);
        entity.setType(type);
        entity.setUserId(params.getUserId());
        entity.setFrom(params.getFrom());
        entity.setTo(params.getTo());
        entity.setDescription(type.getReportTypeId() + " from: " + params.getFrom() + " to: " + params.getTo());

        ReportEntity saveAndFlush = this.reportRepository.saveAndFlush(entity);

        List<AuditEntity> audits = auditRepository.findAllByParam(
                UUID.fromString(params.getUserId()),
                LocalDateTime.of(params.getFrom(), LocalTime.of(0, 0)),
                LocalDateTime.of(params.getTo(), LocalTime.of(0, 0))
        );

        try {
        reportGenerator.generate(audits, saveAndFlush.getId());
        saveAndFlush.setStatus(DONE);

        } catch (IOException e) {
            log.error("Ошибка создания отчета" + e);
            saveAndFlush.setStatus(ERROR);
        }

        reportRepository.save(saveAndFlush);

    }

    @Override
    public Page<ReportEntity> getAllReports(Pageable pageable) {
        return this.reportRepository.findAll(pageable);
    }

    @Override
    public Status getStatusById(String id) {
        return Status.valueOf(this.reportRepository.getStatusById(UUID.fromString(id)));
    }

    @Override
    public ResponseEntity<String> save(UUID uuid) throws IOException {
        String fileName = uuid + ".xlsx";
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists()) {
            byte[] fileContent = Files.readAllBytes(filePath);
            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            );

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(base64Encoded);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
