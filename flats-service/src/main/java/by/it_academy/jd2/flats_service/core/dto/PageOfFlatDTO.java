package by.it_academy.jd2.flats_service.core.dto;

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
public class PageOfFlatDTO {

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

    private List<FlatDTO> content;

    public PageOfFlatDTO(int number, int size) {
        this.number = number;
        this.size = size;
    }
}
