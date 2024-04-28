package by.shershen.user_service.core.dto;

import by.shershen.user_service.core.entity.Role;

import java.util.UUID;

public interface Userable extends Identifiable {
    UUID getId();

    String getMail();

    String getFio();

    Role getRole();
}
