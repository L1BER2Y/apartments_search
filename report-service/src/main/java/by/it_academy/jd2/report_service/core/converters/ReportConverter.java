package by.it_academy.jd2.report_service.core.converters;

import by.it_academy.jd2.report_service.core.converters.api.IReportConverter;
import by.it_academy.jd2.report_service.core.dto.ReportDTO;
import by.it_academy.jd2.report_service.core.dto.UserActionAuditParamDTO;
import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import org.springframework.stereotype.Component;

@Component
public class ReportConverter implements IReportConverter {

    @Override
    public ReportDTO convertReportEntityToDTO(ReportEntity entity) {
        ReportDTO dto = new ReportDTO();
        dto.setUuid(entity.getId());
        dto.setDtCreate(entity.getDtCreate());
        dto.setDtUpdate(entity.getDtUpdate());
        dto.setStatus(entity.getStatus());
        dto.setType(entity.getType());
        dto.setDescription(entity.getDescription());
        dto.setParams(new UserActionAuditParamDTO(entity.getUserId(), entity.getFrom(), entity.getTo()));
        return dto;
    }
}
