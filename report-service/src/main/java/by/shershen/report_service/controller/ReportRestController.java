package by.shershen.report_service.controller;

import by.shershen.report_service.core.converters.api.IPageConverter;
import by.shershen.report_service.core.dto.PageOfReportDTO;
import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.Status;
import by.shershen.report_service.core.entity.Type;
import by.shershen.report_service.service.api.IReportService;
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
    private final IPageConverter pageConverter;

    public ReportRestController(IReportService reportService, IPageConverter pageConverter) {
        this.reportService = reportService;
        this.pageConverter = pageConverter;
    }

    @PostMapping("/{type}")
    public ResponseEntity<String> create(@PathVariable("type") Type type,
                                         @RequestBody UserActionAuditParamDTO dto) {
        this.reportService.create(type, dto);
        return new ResponseEntity<>("Report started", HttpStatus.CREATED);
    }

    @GetMapping
    public PageOfReportDTO<ReportDTO> getPage(@RequestParam(defaultValue =  "1") Integer page,
                                              @RequestParam(defaultValue = "20") Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<ReportDTO> reportPage = this.reportService.getAllReports(pageable);
        return pageConverter.convertPageToPageOfReportDTO(reportPage);
    }

    @GetMapping("/{uuid}/export")
    public ResponseEntity<String> export(@PathVariable("uuid") UUID uuid) throws IOException {
        return reportService.exportReport(uuid);
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/{id}/export")
    public ResponseEntity<Status> status(@PathVariable("id") String id) {
        return switch (reportService.getStatusById(id)) {
            case DONE -> ResponseEntity.status(200).build();
            case ERROR, LOADED, PROGRESS -> ResponseEntity.status(505).build();
        };
    }
}
