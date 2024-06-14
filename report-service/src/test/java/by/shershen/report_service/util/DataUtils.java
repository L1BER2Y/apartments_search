package by.shershen.report_service.util;

import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class DataUtils {

    public static AuditEntity getAuditEntityTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.of(2024, 1, 1, 0, 0))
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .role(Role.USER)
                .fio("Test")
                .text(Action.LOGIN)
                .essenceType(EssenceType.REPORT)
                .essenceId("testId")
                .build();
    }

    public static AuditEntity getAuditEntityOneTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.of(2024, 2, 24, 15, 10))
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .role(Role.USER)
                .fio("Test")
                .text(Action.INFO_ABOUT_ME)
                .essenceType(EssenceType.REPORT)
                .essenceId("testId")
                .build();
    }

    public static AuditEntity getAuditEntityTwoTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.of(2024, 4, 6, 8, 34))
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .role(Role.ADMIN)
                .fio("Test")
                .text(Action.UPDATE_USER)
                .essenceType(EssenceType.REPORT)
                .essenceId("testId")
                .build();
    }

    public static UserActionAuditParamDTO getUserActionAuditParamDTOTransient() {
        return UserActionAuditParamDTO.builder()
                .userId(UUID.randomUUID().toString())
                .from(LocalDate.of(2024, 1, 1))
                .to(LocalDate.now())
                .build();
    }

    public static ReportEntity getReportEntityTransient() {
        return ReportEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .status(Status.PROGRESS)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .userId("Test userId")
                .from(LocalDate.of(2024, 1, 1))
                .to(LocalDate.now())
                .build();
    }

    public static ReportEntity getReportEntityOneTransient() {
        return ReportEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .status(Status.PROGRESS)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .userId("Test userId")
                .from(LocalDate.of(2024, 2, 4))
                .to(LocalDate.now())
                .build();
    }

    public static ReportEntity getReportEntityTwoTransient() {
        return ReportEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .status(Status.PROGRESS)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .userId("Test userId")
                .from(LocalDate.of(2024, 3, 24))
                .to(LocalDate.now())
                .build();
    }

    public static ReportDTO getReportDtoTransient() {
        return ReportDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(Status.PROGRESS)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .params(DataUtils.getUserActionAuditParamDTOTransient())
                .build();
    }

    public static ReportDTO getReportDtoOneTransient() {
        return ReportDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(Status.DONE)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .params(DataUtils.getUserActionAuditParamDTOTransient())
                .build();
    }

    public static ReportDTO getReportDtoTwoTransient() {
        return ReportDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .status(Status.LOADED)
                .type(Type.JOURNAL_AUDIT)
                .description("Test description")
                .params(DataUtils.getUserActionAuditParamDTOTransient())
                .build();
    }
}
