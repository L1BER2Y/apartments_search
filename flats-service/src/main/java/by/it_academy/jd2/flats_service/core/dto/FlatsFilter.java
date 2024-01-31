package by.it_academy.jd2.flats_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class FlatsFilter {

    private String priceFrom;

    private String priceTo;

    private String bedroomsFrom;

    private String bedroomsTo;

    private String areaFrom;

    private String areaTo;

    private String floors;

    private String photo;
}
