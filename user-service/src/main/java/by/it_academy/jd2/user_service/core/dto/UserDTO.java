package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.entity.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private UUID id;

    private LocalDateTime dt_create;

    private LocalDateTime dt_update;

    private String mail;

    private String fio;

    @Enumerated(EnumType.STRING)
    private Role UserRole;

    @Enumerated(EnumType.STRING)
    private Status UserStatus;
}
