package by.it_academy.jd2.audit_service.core.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

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
    private Action action;

    @Enumerated(EnumType.STRING)
    @Column(name = "essence_type")
    private EssenceType essenceType;

    @Column(name = "essence_id")
    private String essenceId;

    public AuditEntity() {
    }

    public AuditEntity(UUID id, LocalDateTime dtCreate, UUID uuid, String mail, String fio, Role role, Action action, EssenceType essenceType, String essenceId) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.action = action;
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

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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
        return Objects.equals(id, that.id) && Objects.equals(dtCreate, that.dtCreate) && Objects.equals(uuid, that.uuid) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && role == that.role && action == that.action && essenceType == that.essenceType && Objects.equals(essenceId, that.essenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dtCreate, uuid, mail, fio, role, action, essenceType, essenceId);
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
                ", action=" + action +
                ", essenceType=" + essenceType +
                ", essenceId='" + essenceId + '\'' +
                '}';
    }
}
