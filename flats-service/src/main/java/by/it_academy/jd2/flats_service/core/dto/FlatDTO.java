package by.it_academy.jd2.flats_service.core.dto;

import by.it_academy.jd2.flats_service.core.entity.OfferType;
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
public class FlatDTO {

    @JsonProperty("uuid")
    private UUID uuid;

    @JsonProperty("dt_create")
    private Long dtCreate;

    @JsonProperty("dt_update")
    private Long dtUpdate;

    @JsonProperty("offer_type")
    private OfferType offerType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("area")
    private Integer area;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("floor")
    private Integer floor;

    @JsonProperty("bedrooms")
    private Integer bedrooms;

    @JsonProperty("photo_urls")
    private String[] photoUrls;

    @JsonProperty("original_url")
    private String originalUrl;
}
