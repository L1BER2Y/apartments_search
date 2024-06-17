package by.shershen.flats_service.util;

import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.FlatsFilter;
import by.shershen.flats_service.core.entity.FlatEntity;
import by.shershen.flats_service.core.entity.OfferType;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class DataUtils {

    public static FlatDTO getFlatDTOTransient() {
        return FlatDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .offerType(OfferType.RENT)
                .description("Test description")
                .bedrooms(2)
                .area(60)
                .price(600)
                .floor(4)
                .photoUrls(new String[] {"Test photo url", "Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatDTO getFlatDTOOneTransient() {
        return FlatDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .offerType(OfferType.RENT)
                .description("Test description")
                .bedrooms(1)
                .area(39)
                .price(350)
                .floor(11)
                .photoUrls(new String[] {"Test photo url", "Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatDTO getFlatDTOTwoTransient() {
        return FlatDTO.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .dtUpdate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .offerType(OfferType.SALE)
                .description("Test description")
                .bedrooms(4)
                .area(120)
                .price(230000)
                .floor(8)
                .photoUrls(new String[] {"Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatEntity getFlatEntityTransient() {
        return FlatEntity.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .offerType(OfferType.RENT)
                .description("Test description")
                .bedrooms(2)
                .area(60)
                .price(600)
                .floor(4)
                .photoUrls(new String[] {"Test photo url", "Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatEntity getFlatEntityOneTransient() {
        return FlatEntity.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .offerType(OfferType.RENT)
                .description("Test description")
                .bedrooms(1)
                .area(39)
                .price(350)
                .floor(11)
                .photoUrls(new String[] {"Test photo url", "Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatEntity getFlatEntityTwoTransient() {
        return FlatEntity.builder()
                .uuid(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .offerType(OfferType.SALE)
                .description("Test description")
                .bedrooms(4)
                .area(120)
                .price(230000)
                .floor(8)
                .photoUrls(new String[] {"Test photo url"})
                .originalUrl("Test original url")
                .build();
    }

    public static FlatsFilter getFlatsFilterTransient() {
        return FlatsFilter.builder()
                .priceFrom(300)
                .priceTo(230000)
                .bedroomsFrom(1)
                .bedroomsTo(4)
                .areaFrom(30)
                .areaTo(120)
                .floors(new Integer[]{4,8,11})
                .photo(true)
                .build();
    }
}
