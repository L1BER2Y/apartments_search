package by.shershen.parser_service.service;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.repository.FlatsRepository;
import by.shershen.parser_service.service.api.ContentRequester;
import by.shershen.parser_service.service.api.Parser;
import by.shershen.parser_service.util.DataUtils;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParsingServiceTests {

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
    void givenContentRequesterAndParser_whenRequestAndSaveFlats_thenRepositoryIsCalled() {
        //given
        Document page = new Document("https://re.kufar.by");
        List<FlatEntity> flats = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityTwoTransient());
        given(contentRequester.getHtmlDocument(any())).willReturn(page);
        given(parser.parse(page)).willReturn(flats);
        //when
        serviceUnderTest.requestAndSaveFlats(contentRequester, parser);
        //then
        verify(flatsRepository, times(1)).saveAll(flats);
    }

    @Test
    @DisplayName("Test already saved flats are not saved again")
    void givenAlreadySavedFlat_whenRequestAndSaveFlats_thenOnlyNewFlatsAreSaved() {
        //given
        Document page = new Document("https://re.kufar.by");
        FlatEntity savedFlat = DataUtils.getFlatEntityTransient();
        FlatEntity newFlat = DataUtils.getFlatEntityTwoTransient();
        newFlat.setOriginalUrl("New original url");
        given(contentRequester.getHtmlDocument(any())).willReturn(page);
        given(parser.parse(page)).willReturn(List.of(savedFlat, newFlat));
        given(flatsRepository.existsByOriginalUrl(savedFlat.getOriginalUrl())).willReturn(true);
        given(flatsRepository.existsByOriginalUrl(newFlat.getOriginalUrl())).willReturn(false);
        //when
        serviceUnderTest.requestAndSaveFlats(contentRequester, parser);
        //then
        verify(flatsRepository, times(1)).saveAll(List.of(newFlat));
    }

    @Test
    @DisplayName("Test nothing is saved when the page is unavailable")
    void givenUnavailablePage_whenRequestAndSaveFlats_thenRepositoryIsNotCalled() {
        //given
        given(contentRequester.getHtmlDocument(any())).willReturn(null);
        //when
        serviceUnderTest.requestAndSaveFlats(contentRequester, parser);
        //then
        verify(parser, never()).parse(any());
        verify(flatsRepository, never()).saveAll(any());
    }
}
