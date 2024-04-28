package by.shershen.report_service.core.dto;

import by.shershen.report_service.core.entity.Role;
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
public class UserDetailsDTO {

    private UUID id;

    private String mail;

    private String fio;

    private Role role;

}
