package by.it_academy.jd2.audit_service.core.dto;

import by.it_academy.jd2.audit_service.core.entity.Action;
import by.it_academy.jd2.audit_service.core.entity.EssenceType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
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
    private UUID uuid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;

    @JsonProperty("user")
    private UserAuditDTO userAuditDTO;

    @JsonProperty("text")
    private Action action;

    @JsonProperty("type")
    private EssenceType type;

    @JsonProperty("id")
    private String typeId;
}
