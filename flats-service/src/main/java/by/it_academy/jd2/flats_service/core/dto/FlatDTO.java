package by.it_academy.jd2.flats_service.core.dto;

import by.it_academy.jd2.flats_service.core.entity.OfferType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlatDTO {

    private UUID uuid;

    private LocalDateTime creationDate;

    private LocalDateTime updatedDate;

    private OfferType offerType;

    private String description;

    private Integer bedrooms;

    private Integer area;

    private Integer price;

    private Integer floor;

    private String[] photoUrls;

    private String originalUrl;
}
