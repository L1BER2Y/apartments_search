package by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;
    private LocalDateTime dt_create;
    private LocalDateTime dt_update;
    private String mail;
    private String fio;
    private Role UserRole;
    private Status UserStatus;

    public UserDTO() {
    }

    public UserDTO(UUID id, LocalDateTime dt_create, LocalDateTime dt_update, String mail, String fio, Role userRole, Status userStatus) {
        this.id = id;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.mail = mail;
        this.fio = fio;
        UserRole = userRole;
        UserStatus = userStatus;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public LocalDateTime getDt_update() {
        return dt_update;
    }

    public void setDt_update(LocalDateTime dt_update) {
        this.dt_update = dt_update;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Role getUserRole() {
        return UserRole;
    }

    public void setUserRole(Role userRole) {
        UserRole = userRole;
    }

    public Status getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(Status userStatus) {
        UserStatus = userStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (dt_create != null ? !dt_create.equals(userDTO.dt_create) : userDTO.dt_create != null) return false;
        if (dt_update != null ? !dt_update.equals(userDTO.dt_update) : userDTO.dt_update != null) return false;
        if (mail != null ? !mail.equals(userDTO.mail) : userDTO.mail != null) return false;
        if (fio != null ? !fio.equals(userDTO.fio) : userDTO.fio != null) return false;
        if (UserRole != userDTO.UserRole) return false;
        return UserStatus == userDTO.UserStatus;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dt_create != null ? dt_create.hashCode() : 0);
        result = 31 * result + (dt_update != null ? dt_update.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (UserRole != null ? UserRole.hashCode() : 0);
        result = 31 * result + (UserStatus != null ? UserStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", dt_create=" + dt_create +
                ", dt_update=" + dt_update +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", UserRole=" + UserRole +
                ", UserStatus=" + UserStatus +
                '}';
    }
}
