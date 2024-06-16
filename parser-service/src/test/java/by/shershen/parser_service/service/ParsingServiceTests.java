package by.shershen.parser_service.service;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.repository.FlatsRepository;
import by.shershen.parser_service.service.api.ContentRequester;
import by.shershen.parser_service.service.api.Parser;
import by.shershen.parser_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ParsingServiceTests {

    @Mock
    private FlatsRepository flatsRepository;

    @Mock
    private RentContentRequester rentContentRequester;

    @Mock
    private SaleContentRequester saleContentRequester;

    @Mock
    private SaleParser saleParser;

    @Mock
    private RentParser rentParser;

    @Mock
    private ContentRequester contentRequester;

    @Mock
    private Parser parser;

    @InjectMocks
    private ParsingService serviceUnderTest;

    @Test
    @DisplayName("Test request and save flats functionality")
    public void givenContentRequesterAndParser_whenRequestAndSaveFlats_thenRepositoryIsCalled() {
        //given
        //when
        serviceUnderTest.requestAndSaveFlats(contentRequester, parser);
        //then
        verify(flatsRepository, times(1)).saveAll(any());
    }
}
