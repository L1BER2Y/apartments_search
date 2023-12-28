package by.it_academy.jd2.user_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerificationDTO {
    private String code;
    private String mail;
}
