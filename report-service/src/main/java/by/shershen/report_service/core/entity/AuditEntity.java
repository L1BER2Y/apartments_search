package by.shershen.report_service.core.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(schema = "users", name = "audits")
public class AuditEntity {
    @Id
    private UUID id;

    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    private UUID uuid;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Action text;

    @Enumerated(EnumType.STRING)
    @Column(name = "essence_type")
    private EssenceType essenceType;

    @Column(name = "essence_id")
    private String essenceId;

    public AuditEntity() {
    }

    public AuditEntity(UUID id, LocalDateTime dtCreate, UUID uuid, String mail, String fio, Role role, Action text, EssenceType essenceType, String essenceId) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.essenceType = essenceType;
        this.essenceId = essenceId;
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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Action getText() {
        return text;
    }

    public void setText(Action text) {
        this.text = text;
    }

    public EssenceType getEssenceType() {
        return essenceType;
    }

    public void setEssenceType(EssenceType essenceType) {
        this.essenceType = essenceType;
    }

    public String getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(String essenceId) {
        this.essenceId = essenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditEntity that = (AuditEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dtCreate != null ? !dtCreate.equals(that.dtCreate) : that.dtCreate != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;
        if (role != that.role) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (essenceType != that.essenceType) return false;
        return essenceId != null ? essenceId.equals(that.essenceId) : that.essenceId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dtCreate != null ? dtCreate.hashCode() : 0);
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (essenceType != null ? essenceType.hashCode() : 0);
        result = 31 * result + (essenceId != null ? essenceId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "id=" + id +
                ", dtCreate=" + dtCreate +
                ", uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", text='" + text + '\'' +
                ", essenceType=" + essenceType +
                ", essenceId='" + essenceId + '\'' +
                '}';
    }
}
