package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTOBuilder {
    private UUID id;
    private LocalDateTime dt_create;
    private LocalDateTime dt_update;
    private String mail;
    private String fio;
    private Role userRole;
    private Status userStatus;
    private String password;

    public UserDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public UserDTOBuilder setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
        return this;
    }

    public UserDTOBuilder setDt_update(LocalDateTime dt_update) {
        this.dt_update = dt_update;
        return this;
    }

    public UserDTOBuilder setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public UserDTOBuilder setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public UserDTOBuilder setUserRole(Role userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserDTOBuilder setUserStatus(Status userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public UserDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO build() {
        return new UserDTO(id, dt_create, dt_update, mail, fio, userRole, userStatus, password);
    }
}