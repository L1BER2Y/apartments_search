package by.it_academy.jd2.user_service.core.dto;

import by.it_academy.jd2.user_service.core.entity.AuditedAction;
import by.it_academy.jd2.user_service.core.entity.EssenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class AuditDTO {

    @JsonProperty("uuid")
    private UUID uuid = UUID.randomUUID();

    @JsonProperty("dt_create")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtCreate = LocalDateTime.now();

    @JsonProperty("user")
    private UserAuditDTO userAuditDTO;

    @JsonProperty("text")
    private AuditedAction action;

    @JsonProperty("type")
    private EssenceType type;

    @JsonProperty("id")
    private String typeId;

}
