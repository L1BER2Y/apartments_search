package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private UUID userId;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private Role role;
}
