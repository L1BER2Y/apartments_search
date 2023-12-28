package by.it_academy.jd2.user_service.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(schema = "verify", name = "codes")
public class VerificationEntity {
    @Id
    private Integer id;
    private String code;
    private String mail;

    public VerificationEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationEntity that = (VerificationEntity) o;
        return id == that.id && Objects.equals(code, that.code) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, mail);
    }

    @Override
    public String toString() {
        return "VerificationEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
