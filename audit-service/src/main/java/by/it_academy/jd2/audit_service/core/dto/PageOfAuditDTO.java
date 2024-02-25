package by.it_academy.jd2.audit_service.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class PageOfAuditDTO<AuditDTO> {

    @JsonProperty("number")
    private Integer number;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

    @JsonProperty("first")
    private Boolean first;

    @JsonProperty("number_of_elements")
    private Integer numberOfElements;

    @JsonProperty("last")
    private Boolean last;

    @JsonProperty("content")
    private List<AuditDTO> content;

}
