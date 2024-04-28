package by.shershen.report_service.core.converters;

import by.shershen.report_service.core.converters.api.IReportConverter;
import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.ReportEntity;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ReportConverter implements IReportConverter {

    @Override
    public ReportDTO convertReportEntityToDTO(ReportEntity entity) {
        ReportDTO dto = new ReportDTO();
        dto.setUuid(entity.getId());
        dto.setDtCreate(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setDtUpdate(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        dto.setParams(new UserActionAuditParamDTO(entity.getUserId(), entity.getFrom(), entity.getTo()));
        return dto;
    }
}
