package by.shershen.audit_service.util;

import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.UserAuditDTO;
import by.shershen.audit_service.core.entity.Action;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.core.entity.EssenceType;
import by.shershen.audit_service.core.entity.Role;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class DataUtils {

    public static AuditEntity getAuditEntityTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .text(Action.LOGIN)
                .essenceType(EssenceType.USER)
                .essenceId("test id")
                .build();
    }

    public static AuditEntity getAuditEntityOneTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.ADMIN)
                .text(Action.INFO_ABOUT_ALL_USERS)
                .essenceType(EssenceType.USER)
                .essenceId("test id")
                .build();
    }

    public static AuditEntity getAuditEntityTwoTransient() {
        return AuditEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .uuid(UUID.randomUUID())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.ADMIN)
                .text(Action.INFO_ABOUT_USER_BY_ID)
                .essenceType(EssenceType.REPORT)
                .essenceId("test id")
                .build();
    }

    public static AuditDTO getAuditDTOTransient() {
        return AuditDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .userAuditDTO(new UserAuditDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER))
                .action(Action.LOGIN)
                .type(EssenceType.USER)
                .typeId("test id")
                .build();
    }

    public static AuditInfoDTO getAuditInfoDTOTransient() {
        return AuditInfoDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .userAuditDTO(new UserAuditDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER))
                .action(Action.LOGIN)
                .type(EssenceType.USER)
                .typeId("test id")
                .build();
    }

    public static AuditInfoDTO getAuditInfoDTOOneTransient() {
        return AuditInfoDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .userAuditDTO(new UserAuditDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.ADMIN))
                .action(Action.REGISTRATION)
                .type(EssenceType.USER)
                .typeId("test id")
                .build();
    }

    public static AuditInfoDTO getAuditInfoDTOTwoTransient() {
        return AuditInfoDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .userAuditDTO(new UserAuditDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.MANAGER))
                .action(Action.INFO_ABOUT_ALL_USERS)
                .type(EssenceType.REPORT)
                .typeId("test id")
                .build();
    }
}
