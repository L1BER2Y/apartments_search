package by.it_academy.jd2.report_service.controller;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import by.it_academy.jd2.report_service.service.api.IReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping( "/report")
public class ReportRestController {

    private final IReportService reportService;

    public ReportRestController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<String> create(@PathVariable("type") Type type,
                                         @RequestBody UserActionAuditParamDTO dto) {
        this.reportService.create(type, dto);
        return new ResponseEntity<>("Отчёт запущен", HttpStatus.CREATED);
    }

    @GetMapping
    public Page<ReportEntity> getPage(@RequestParam(defaultValue =  "1") Integer page,
                                      @RequestParam(defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return this.reportService.getAllReports(pageable);
    }

    @GetMapping("/{uuid}/export")
    public ResponseEntity<String> save(@PathVariable("uuid") UUID uuid) throws IOException {
        return reportService.save(uuid);
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/{id}/export")
    public ResponseEntity<Status> status(@PathVariable("id") String id) {
        return switch (reportService.getStatusById(id)) {
            case DONE -> ResponseEntity.status(200).build();
            case ERROR, LOADED, PROGRESS -> ResponseEntity.status(505).build();
        };
    }
}
