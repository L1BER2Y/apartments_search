package by.it_academy.jd2.report_service.service.api;

import by.it_academy.jd2.report_service.core.dto.ReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import by.it_academy.jd2.report_service.core.entity.Status;
import by.it_academy.jd2.report_service.core.entity.Type;
import by.it_academy.jd2.report_service.repository.ReportRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService{

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
    public Page<ReportEntity> getAllReports(Pageable pageable) {
        return null;
    }

    @Override
    public Status getStatusById(String id) {
        return null;
    }
}
