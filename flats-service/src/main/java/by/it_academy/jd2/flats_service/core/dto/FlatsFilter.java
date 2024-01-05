package by.it_academy.jd2.flats_service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlatsFilter {

    private int priceFrom;

    private int priceTo;

    private int bedroomsFrom;

    private int bedroomsTo;

    private int areaFrom;

    private int areaTo;

    private int[] floors;

    private boolean photo;
}
