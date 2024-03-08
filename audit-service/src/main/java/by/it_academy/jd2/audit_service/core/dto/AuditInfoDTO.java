package by.it_academy.jd2.audit_service.core.dto;

import by.it_academy.jd2.audit_service.core.entity.Action;
import by.it_academy.jd2.audit_service.core.entity.EssenceType;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AuditInfoDTO {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("user")
    private UserAuditDTO userAuditDTO;

    @JsonProperty("text")
    private Action action;

    @JsonProperty("type")
    private EssenceType type;

    @JsonProperty("id")
    private String typeId;
}
