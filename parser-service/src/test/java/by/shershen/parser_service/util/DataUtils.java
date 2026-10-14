package by.shershen.parser_service.util;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.core.entity.OfferType;

public class DataUtils {

    public static FlatEntity getFlatEntityTransient() {
        return FlatEntity.builder()
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
                .offerType(OfferType.RENT)
                .description("Test description")
                .bedrooms(1)
                .area(39)
                .price(350)
                .floor(11)
                .photoUrls(new String[] {"Test photo url", "Test photo url"})
                .originalUrl("Test original url one")
                .build();
    }

    public static FlatEntity getFlatEntityTwoTransient() {
        return FlatEntity.builder()
                .offerType(OfferType.SALE)
                .description("Test description")
                .bedrooms(4)
                .area(120)
                .price(230000)
                .floor(8)
                .photoUrls(new String[] {"Test photo url"})
                .originalUrl("Test original url two")
                .build();
    }
}
