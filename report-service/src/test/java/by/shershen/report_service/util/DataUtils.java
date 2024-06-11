package by.shershen.report_service.util;

import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.Action;
import by.shershen.report_service.core.entity.AuditEntity;
import by.shershen.report_service.core.entity.EssenceType;
import by.shershen.report_service.core.entity.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
}
