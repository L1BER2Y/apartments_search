package by.shershen.parser_service.scheduler;

import by.shershen.parser_service.config.ParsingProperties;
import by.shershen.parser_service.config.ParsingProperties.Source;
import by.shershen.parser_service.core.entity.OfferType;
import by.shershen.parser_service.service.ParsingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParsingSchedulerTests {

    private static final Source RENT = new Source(OfferType.RENT, "rent-url");
    private static final Source SALE = new Source(OfferType.SALE, "sale-url");

    @Mock
    private ParsingService parsingService;

    private ParsingScheduler schedulerUnderTest;

    @BeforeEach
    void setUp() {
        ParsingProperties properties = new ParsingProperties(1, Duration.ZERO, List.of(RENT, SALE));
        schedulerUnderTest = new ParsingScheduler(parsingService, properties);
    }

    @Test
    @DisplayName("Test all sources are parsed in the configured order")
    void givenTwoSources_whenParseAllSources_thenBothAreParsedInOrder() {
        //given
        InOrder order = inOrder(parsingService);
        //when
        schedulerUnderTest.parseAllSources();
        //then
        order.verify(parsingService).parse(RENT);
        order.verify(parsingService).parse(SALE);
    }

    @Test
    @DisplayName("Test a failed source does not stop the next one")
    void givenFailingFirstSource_whenParseAllSources_thenSecondSourceIsParsed() {
        //given
        willThrow(new IllegalStateException("layout has changed")).given(parsingService).parse(RENT);
        //when
        schedulerUnderTest.parseAllSources();
        //then
        verify(parsingService).parse(SALE);
    }
}
