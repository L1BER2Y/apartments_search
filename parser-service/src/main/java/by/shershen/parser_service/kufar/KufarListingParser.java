package by.shershen.parser_service.kufar;

import by.shershen.parser_service.core.ListingPage;
import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.core.entity.OfferType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.StreamSupport;

@Slf4j
@Component
@RequiredArgsConstructor
public class KufarListingParser {

    private static final String PHOTO_URL_PREFIX = "https://rms.kufar.by/v1/list_thumbs_2x/";

    private final ObjectMapper objectMapper;

    public ListingPage parse(Document page, OfferType offerType) {
        JsonNode listing = readListing(page);
        return new ListingPage(findFlats(listing, offerType), findNextPageUrl(listing, page));
    }

    private List<FlatEntity> findFlats(JsonNode listing, OfferType offerType) {
        return StreamSupport.stream(listing.path("ads").spliterator(), false)
                .map(ad -> tryConvertToFlat(ad, offerType))
                .filter(Objects::nonNull)
                .toList();
    }

    private String findNextPageUrl(JsonNode listing, Document page) {
        for (JsonNode pagination : listing.path("pagination")) {
            if ("next".equals(text(pagination.path("label")))) {
                String baseUrl = StringUtils.substringBefore(page.location(), "?");
                return baseUrl + "?cursor=" + text(pagination.path("token"));
            }
        }
        return null;
    }

    private JsonNode readListing(Document page) {
        Element script = page.getElementById("__NEXT_DATA__");
        if (script == null) {
            throw new IllegalStateException("Kufar page has no __NEXT_DATA__ script, the page layout has changed");
        }
        try {
            return objectMapper.readTree(script.data()).path("props").path("initialState").path("listing");
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Can't read Kufar listing JSON", e);
        }
    }

    private FlatEntity tryConvertToFlat(JsonNode ad, OfferType offerType) {
        try {
            return convertToFlat(ad, offerType);
        } catch (Exception exception) {
            log.warn("Skipped Kufar ad {}: {}", ad.path("ad_id").asText(), exception.toString());
            return null;
        }
    }

    private FlatEntity convertToFlat(JsonNode ad, OfferType offerType) {
        String originalUrl = text(ad.path("ad_link"));
        if (originalUrl.isBlank()) {
            throw new IllegalArgumentException("ad has no link");
        }
        Map<String, JsonNode> parameters = findParameters(ad);

        return FlatEntity.builder()
                .offerType(offerType)
                .description(findDescription(ad))
                .bedrooms(findBedrooms(parameters.get("rooms")))
                .area(findArea(parameters.get("size")))
                .price(findPrice(ad))
                .floor(findFloor(parameters.get("floor")))
                .photoUrls(findPhotoUrls(ad))
                .originalUrl(originalUrl)
                .build();
    }

    private Map<String, JsonNode> findParameters(JsonNode ad) {
        Map<String, JsonNode> parameters = new HashMap<>();
        for (JsonNode parameter : ad.path("ad_parameters")) {
            parameters.put(text(parameter.path("p")), parameter.path("v"));
        }
        return parameters;
    }

    private String findDescription(JsonNode ad) {
        String description = text(ad.path("body_short"));
        return description.isBlank() ? ad.path("subject").textValue() : description;
    }

    private Integer findBedrooms(JsonNode rooms) {
        if (rooms == null) {
            return null;
        }
        String digits = text(rooms).replaceAll("\\D", "");
        return digits.isEmpty() ? null : Integer.valueOf(digits);
    }

    private Integer findArea(JsonNode size) {
        return size == null ? null : (int) size.asDouble();
    }

    private Integer findFloor(JsonNode floor) {
        if (floor == null) {
            return null;
        }
        JsonNode value = floor.isArray() ? floor.path(0) : floor;
        return value.isMissingNode() ? null : value.asInt();
    }

    private Integer findPrice(JsonNode ad) {
        String cents = text(ad.path("price_usd"));
        return cents.isBlank() ? null : (int) (Long.parseLong(cents) / 100);
    }

    private String[] findPhotoUrls(JsonNode ad) {
        String[] photoUrls = StreamSupport.stream(ad.path("images").spliterator(), false)
                .filter(image -> "rms".equals(text(image.path("media_storage"))))
                .map(image -> PHOTO_URL_PREFIX + text(image.path("path")))
                .toArray(String[]::new);
        return photoUrls.length == 0 ? null : photoUrls;
    }

    private static String text(JsonNode node) {
        return node.isNull() || node.isMissingNode() ? "" : node.asText();
    }
}
