package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.Role;
import by.it_academy.jd2.user_service.core.entity.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtCreate;

    @JsonProperty("dt_update")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtUpdate;

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
