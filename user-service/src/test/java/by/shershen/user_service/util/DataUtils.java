package by.shershen.user_service.util;

import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserLoginDTO;
import by.shershen.user_service.core.dto.UserRegDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class DataUtils {

    public static UserEntity getUserTransient() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserEntity getUserOneTransient() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserEntity getUserTwoTransient() {
        return UserEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserEntity getUserPersisted() {
        return UserEntity.builder()
                .id(UUID.fromString("eaf4ca36-e201-43c9-9276-3e273ec93f27"))
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserEntity getUserOnePersisted() {
        return UserEntity.builder()
                .id(UUID.fromString("0ee5a33e-a365-456d-827d-fd99b733aed9"))
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserEntity getUserTwoPersisted() {
        return UserEntity.builder()
                .id(UUID.fromString("431fb60d-2ae8-4b59-92b6-00f931401b85"))
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
    }

    public static UserDTO getUserDTOPersisted() {
        return UserDTO.builder()
                .id(UUID.fromString("eaf4ca36-e201-43c9-9276-3e273ec93f27"))
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .build();
    }

    public static UserLoginDTO getUserLoginDTOTransient() {
        return UserLoginDTO.builder()
                .mail("test@mail.com")
                .password("test")
                .build();
    }

    public static VerificationEntity getVerificationEntityTransient() {
        return VerificationEntity.builder()
                .code("code")
                .id(UUID.randomUUID())
                .mail("test@mail.com")
                .sendCode(true)
                .build();
    }

    public static UserRegDTO getUserRegDTOTransient() {
        return UserRegDTO.builder()
                .mail("test@mail.com")
                .fio("Test")
                .password("test")
                .build();
    }
}
