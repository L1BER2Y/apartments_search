package by.shershen.user_service.core.dto;

import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserCreateDTO {

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("fio")
    private String fio;

    @JsonProperty("role")
    private Role role;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("password")
    private String password;

}
