package by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.Role;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserEntity {
    private UUID id;
    private LocalDateTime dt_create;
    private LocalDateTime dt_update;
    private String mail;
    private String fio;
    private Role UserRole;
    private Status UserStatus;
    private String password;

    public UserEntity() {
    }

    public UserEntity(UUID id, LocalDateTime dt_create, LocalDateTime dt_update, String mail, String fio, Role userRole, Status userStatus, String password) {
        this.id = id;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.mail = mail;
        this.fio = fio;
        UserRole = userRole;
        UserStatus = userStatus;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity user = (UserEntity) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (dt_create != null ? !dt_create.equals(user.dt_create) : user.dt_create != null) return false;
        if (dt_update != null ? !dt_update.equals(user.dt_update) : user.dt_update != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (fio != null ? !fio.equals(user.fio) : user.fio != null) return false;
        if (UserRole != user.UserRole) return false;
        if (UserStatus != user.UserStatus) return false;
        return password != null ? password.equals(user.password) : user.password == null;
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
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dt_create=" + dt_create +
                ", dt_update=" + dt_update +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", UserRole=" + UserRole +
                ", UserStatus=" + UserStatus +
                ", password='" + password + '\'' +
                '}';
    }
}
