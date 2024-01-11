package by.it_academy.jd2.report_service.service;

import by.it_academy.jd2.report_service.core.dto.PageOfReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import by.it_academy.jd2.report_service.repository.ReportRepository;
import by.it_academy.jd2.report_service.service.api.IReportService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportService implements IReportService {

    private final ReportRepository repository;

    private final ModelMapper mapper;

    public ReportService(ReportRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void createReport(Type type, UserActionAuditParamDTO params) {
    }

    @Override
    public Page<ReportEntity> getAllReports(PageOfReportDTO page) {
        return this.repository.findAll(PageRequest.of(page.getNumber(), page.getSize()));
    }

    @Override
    public Status getStatusById(String id) {
        return Status.valueOf(this.repository.getStatusById(UUID.fromString(id)));
    }

    @Override
    public String save(UUID uuid) {
        return null;
    }
}
