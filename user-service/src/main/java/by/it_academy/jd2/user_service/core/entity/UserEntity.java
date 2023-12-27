package by.it_academy.jd2.user_service.core.entity;

import by.it_academy.jd2.user_service.core.dto.Status;
import jakarta.persistence.*;
import by.it_academy.jd2.user_service.core.dto.Role;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "user", name = "user")
public class UserEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private Role UserRole;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private Status UserStatus;
    @Column(name = "password")
    private String password;

    public UserEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
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
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && UserRole == that.UserRole && UserStatus == that.UserStatus && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, dtUpdate, mail, fio, UserRole, UserStatus, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", UserRole=" + UserRole +
                ", UserStatus=" + UserStatus +
                ", password='" + password + '\'' +
                '}';
    }
}
