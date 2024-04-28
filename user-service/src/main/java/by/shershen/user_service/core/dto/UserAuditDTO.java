package by.shershen.user_service.core.dto;

import by.shershen.user_service.core.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserAuditDTO {

    private UUID uuid;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private Role role;
}
