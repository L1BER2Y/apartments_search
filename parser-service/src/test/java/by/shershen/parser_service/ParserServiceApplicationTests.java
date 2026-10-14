package by.shershen.parser_service;

import by.shershen.parser_service.config.ParsingProperties;
import by.shershen.parser_service.config.ParsingProperties.Source;
import by.shershen.parser_service.core.entity.OfferType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@ActiveProfiles("parser-test")
class ParserServiceApplicationTests {

    @Autowired
    private ParsingProperties properties;

    @Test
    @DisplayName("Test the context starts and both Kufar listings are configured")
    void givenApplicationYml_whenContextStarts_thenSourcesAreBound() {
        //given
        //when
        //then
        assertThat(properties.sources())
                .extracting(Source::offerType)
                .containsExactly(OfferType.RENT, OfferType.SALE);
    }
}
