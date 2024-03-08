package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {

    @JsonProperty("uuid")
    private UUID id;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("dt_update")
    private Long dtUpdate;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("fio")
    private String fio;

    @Enumerated(EnumType.STRING)
    @JsonProperty("role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @JsonProperty("status")
    private Status status;
}
