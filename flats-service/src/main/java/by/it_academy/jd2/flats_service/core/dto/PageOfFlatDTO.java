package by.it_academy.jd2.flats_service.core.dto;

import by.it_academy.jd2.flats_service.core.entity.FlatEntity;
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
public class PageOfFlatDTO {

    private Integer number;
    private Integer size;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

    private Boolean first;

    @JsonProperty("number_of_elements")
    private Integer numberOfElements;

    private Boolean last;

    private List<FlatEntity> content;

    public PageOfFlatDTO(Integer number, Integer size) {
        this.number = number;
        this.size = size;
    }
}
