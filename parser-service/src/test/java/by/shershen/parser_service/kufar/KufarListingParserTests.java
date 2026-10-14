package by.shershen.parser_service.kufar;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.core.entity.OfferType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KufarListingParserTests {

    private static final String PAGE_URL = "https://re.kufar.by/l/minsk/kupit/kvartiru?cur=USD&size=30";

    private final KufarListingParser parser = new KufarListingParser(new ObjectMapper());

    private Document page;

    @BeforeEach
    void setUp() throws IOException {
        try (InputStream html = getClass().getResourceAsStream("/kufar/listing.html")) {
            page = Jsoup.parse(html, StandardCharsets.UTF_8.name(), PAGE_URL);
        }
    }

    @Test
    @DisplayName("Test parsing all fields of a sale ad")
    void givenListingPage_whenParse_thenFlatFieldsAreFilled() {
        //given
        //when
        List<FlatEntity> flats = parser.parse(page, OfferType.SALE).flats();
        //then
        FlatEntity flat = flats.get(0);
        assertThat(flat.getOfferType()).isEqualTo(OfferType.SALE);
        assertThat(flat.getOriginalUrl()).isEqualTo("https://re.kufar.by/vi/1");
        assertThat(flat.getDescription()).isEqualTo("Светлая квартира у метро");
        assertThat(flat.getPrice()).isEqualTo(117504);
        assertThat(flat.getBedrooms()).isEqualTo(2);
        assertThat(flat.getArea()).isEqualTo(43);
        assertThat(flat.getFloor()).isEqualTo(13);
        assertThat(flat.getPhotoUrls()).containsExactly(
                "https://rms.kufar.by/v1/list_thumbs_2x/adim1/first.jpg",
                "https://rms.kufar.by/v1/list_thumbs_2x/adim1/second.jpg");
    }

    @Test
    @DisplayName("Test parsing an ad with missing optional fields")
    void givenAdWithoutOptionalFields_whenParse_thenFieldsAreNullOrFallback() {
        //given
        //when
        FlatEntity flat = parser.parse(page, OfferType.RENT).flats().get(1);
        //then
        assertThat(flat.getOfferType()).isEqualTo(OfferType.RENT);
        assertThat(flat.getDescription()).isEqualTo("Квартира");
        assertThat(flat.getPrice()).isEqualTo(600);
        assertThat(flat.getBedrooms()).isEqualTo(5);
        assertThat(flat.getFloor()).isNull();
        assertThat(flat.getPhotoUrls()).isNull();
    }

    @Test
    @DisplayName("Test that broken ads are skipped")
    void givenAdWithoutLink_whenParse_thenAdIsSkipped() {
        //given
        //when
        List<FlatEntity> flats = parser.parse(page, OfferType.SALE).flats();
        //then
        assertThat(flats).hasSize(2);
    }

    @Test
    @DisplayName("Test next page link is built from the pagination cursor")
    void givenListingPage_whenParseNextPage_thenCursorUrlIsReturned() {
        //given
        //when
        String nextPage = parser.parse(page, OfferType.SALE).nextPageUrl();
        //then
        assertThat(nextPage).isEqualTo("https://re.kufar.by/l/minsk/kupit/kvartiru?cursor=eyJwIjoyfQ==");
    }

    @Test
    @DisplayName("Test page without embedded listing data")
    void givenPageWithoutNextData_whenParse_thenExceptionIsThrown() {
        //given
        Document emptyPage = Jsoup.parse("<html><body></body></html>", PAGE_URL);
        //when
        //then
        assertThatThrownBy(() -> parser.parse(emptyPage, OfferType.SALE)).isInstanceOf(IllegalStateException.class);
    }
}
