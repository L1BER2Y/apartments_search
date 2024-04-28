package by.shershen.report_service.service.api;

import by.shershen.report_service.core.entity.AuditEntity;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface IReportGenerator {

    void generate(List<AuditEntity> reports, UUID name) throws IOException;
}
