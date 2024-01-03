package by.it_academy.jd2.audit_service.core.dto;

import by.it_academy.jd2.audit_service.core.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UUID uuid;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private Role role;
}
