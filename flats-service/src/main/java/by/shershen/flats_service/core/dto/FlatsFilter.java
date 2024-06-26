package by.shershen.flats_service.core.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class FlatsFilter {

    private Integer priceFrom;

    private Integer priceTo;

    private Integer bedroomsFrom;

    private Integer bedroomsTo;

    private Integer areaFrom;

    private Integer areaTo;

    private Integer[] floors;

    private Boolean photo;
}
