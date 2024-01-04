package by.it_academy.jd2.report_service.core.dto;

import by.it_academy.jd2.report_service.core.entity.ReportEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageOfReportDTO {
    private int number;
    private int size;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_elements")
    private long totalElements;

    private boolean first;

    @JsonProperty("number_of_elements")
    private int numberOfElements;

    private boolean last;

    private List<ReportDTO> content;

    public PageOfReportDTO(int number, int size) {
        this.number = number;
        this.size = size;
    }

}
