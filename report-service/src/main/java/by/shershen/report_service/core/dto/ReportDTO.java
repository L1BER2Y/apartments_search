package by.shershen.report_service.core.dto;

import by.shershen.report_service.core.entity.Status;
import by.shershen.report_service.core.entity.Type;
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
public class ReportDTO {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("dt_update")
    private Long dtUpdate;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("type")
    private Type type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("params")
    private UserActionAuditParamDTO params;
}
