package by.it_academy.jd2.report_service.controller;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import by.it_academy.jd2.report_service.service.api.IReportService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( "/report")
public class ReportRestController {

    private final IReportService reportService;

    public ReportRestController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("{type}")
    public ResponseEntity<String> start(@PathVariable("type") Type type,
                                        @RequestBody UserActionAuditParamDTO dto) {
        this.reportService.createReport(type, dto);
        return new ResponseEntity<>("Отчёт запущен", HttpStatus.CREATED);
    }

    @GetMapping
    public PageOfReportDTO getPage(@RequestParam(defaultValue =  "0") Integer page,
                                   @RequestParam(defaultValue = "20") Integer size) {
        PageOfReportDTO pageable = new PageOfReportDTO(page, size);
        Page<ReportEntity> reportsPage = this.reportService.getAllReports(pageable);
        return new PageOfReportDTO(reportsPage.getNumber(), reportsPage.getSize(),
                reportsPage.getTotalPages(), reportsPage.getTotalElements(), reportsPage.isFirst(),
                reportsPage.getNumberOfElements(), reportsPage.isLast(), reportsPage.getContent());
    }

    @GetMapping("/{uuid}/export")
    public String save(@PathVariable("uuid") UUID uuid){
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
