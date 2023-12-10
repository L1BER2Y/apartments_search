package by.it_academy.jd2.Mk_JD2_103_23.user_service.dao.entity;

import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.Role;
import by.it_academy.jd2.Mk_JD2_103_23.user_service.core.dto.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private UUID id;
    @Column(name = "dtCreate")
    private LocalDateTime dtCreate;
    @Column(name = "dtUpdate")
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private Role UserRole;
    private Status UserStatus;
    private String password;

    public UserEntity() {
    }

    public UserEntity(UUID id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail, String fio, Role userRole, Status userStatus, String password) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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

        UserEntity user = (UserEntity) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (dtCreate != null ? !dtCreate.equals(user.dtCreate) : user.dtCreate != null) return false;
        if (dtUpdate != null ? !dtUpdate.equals(user.dtUpdate) : user.dtUpdate != null) return false;
        if (mail != null ? !mail.equals(user.mail) : user.mail != null) return false;
        if (fio != null ? !fio.equals(user.fio) : user.fio != null) return false;
        if (UserRole != user.UserRole) return false;
        if (UserStatus != user.UserStatus) return false;
        return password != null ? password.equals(user.password) : user.password == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dtCreate != null ? dtCreate.hashCode() : 0);
        result = 31 * result + (dtUpdate != null ? dtUpdate.hashCode() : 0);
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
