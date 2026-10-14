package by.shershen.parser_service.service;

import by.shershen.parser_service.config.ParsingProperties;
import by.shershen.parser_service.config.ParsingProperties.Source;
import by.shershen.parser_service.core.ListingPage;
import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.core.entity.OfferType;
import by.shershen.parser_service.kufar.KufarClient;
import by.shershen.parser_service.kufar.KufarListingParser;
import by.shershen.parser_service.repository.FlatsRepository;
import by.shershen.parser_service.util.DataUtils;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ParsingServiceTests {

    private static final int MAX_PAGES = 3;
    private static final String FIRST_PAGE_URL = "first-page-url";
    private static final String SECOND_PAGE_URL = "second-page-url";
    private static final Source SOURCE = new Source(OfferType.RENT, FIRST_PAGE_URL);

    @Mock
    private KufarClient client;

    @Mock
    private KufarListingParser parser;

    @Mock
    private FlatsRepository flatsRepository;

    private ParsingService serviceUnderTest;

    @BeforeEach
    void setUp() {
        ParsingProperties properties = new ParsingProperties(MAX_PAGES, Duration.ZERO, List.of(SOURCE));
        serviceUnderTest = new ParsingService(client, parser, flatsRepository, properties);
    }

    @Test
    @DisplayName("Test parse and save flats functionality")
    void givenListingPage_whenParse_thenFlatsAreSaved() {
        //given
        Document page = new Document(FIRST_PAGE_URL);
        List<FlatEntity> flats = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityTwoTransient());
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.of(page));
        given(parser.parse(page, OfferType.RENT)).willReturn(new ListingPage(flats, null));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(flatsRepository, times(1)).saveAll(flats);
    }

    @Test
    @DisplayName("Test already saved flats are not saved again")
    void givenAlreadySavedFlat_whenParse_thenOnlyNewFlatsAreSaved() {
        //given
        Document page = new Document(FIRST_PAGE_URL);
        FlatEntity savedFlat = DataUtils.getFlatEntityTransient();
        FlatEntity newFlat = DataUtils.getFlatEntityTwoTransient();
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.of(page));
        given(parser.parse(page, OfferType.RENT)).willReturn(new ListingPage(List.of(savedFlat, newFlat), null));
        given(flatsRepository.findExistingOriginalUrls(List.of(savedFlat.getOriginalUrl(), newFlat.getOriginalUrl())))
                .willReturn(Set.of(savedFlat.getOriginalUrl()));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(flatsRepository, times(1)).saveAll(List.of(newFlat));
    }

    @Test
    @DisplayName("Test the same flat met twice on a page is saved once")
    void givenDuplicateFlatsOnPage_whenParse_thenFlatIsSavedOnce() {
        //given
        Document page = new Document(FIRST_PAGE_URL);
        FlatEntity flat = DataUtils.getFlatEntityTransient();
        FlatEntity duplicate = DataUtils.getFlatEntityTransient();
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.of(page));
        given(parser.parse(page, OfferType.RENT)).willReturn(new ListingPage(List.of(flat, duplicate), null));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(flatsRepository, times(1)).saveAll(List.of(flat));
    }

    @Test
    @DisplayName("Test nothing is saved when the page is unavailable")
    void givenUnavailablePage_whenParse_thenRepositoryIsNotCalled() {
        //given
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.empty());
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(parser, never()).parse(any(), any());
        verify(flatsRepository, never()).saveAll(any());
    }

    @Test
    @DisplayName("Test all pages are parsed until there is no next page")
    void givenTwoPages_whenParse_thenBothPagesAreSaved() {
        //given
        Document firstPage = new Document(FIRST_PAGE_URL);
        Document secondPage = new Document(SECOND_PAGE_URL);
        List<FlatEntity> firstFlats = List.of(DataUtils.getFlatEntityTransient());
        List<FlatEntity> secondFlats = List.of(DataUtils.getFlatEntityTwoTransient());
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.of(firstPage));
        given(client.fetch(SECOND_PAGE_URL)).willReturn(Optional.of(secondPage));
        given(parser.parse(firstPage, OfferType.RENT)).willReturn(new ListingPage(firstFlats, SECOND_PAGE_URL));
        given(parser.parse(secondPage, OfferType.RENT)).willReturn(new ListingPage(secondFlats, null));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(flatsRepository, times(1)).saveAll(firstFlats);
        verify(flatsRepository, times(1)).saveAll(secondFlats);
        verify(client, times(2)).fetch(anyString());
    }

    @Test
    @DisplayName("Test parsing stops at the page limit")
    void givenEndlessPagination_whenParse_thenOnlyMaxPagesAreRequested() {
        //given
        Document page = new Document(FIRST_PAGE_URL);
        given(client.fetch(anyString())).willReturn(Optional.of(page));
        given(parser.parse(page, OfferType.RENT)).willReturn(new ListingPage(List.of(), SECOND_PAGE_URL));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(client, times(MAX_PAGES)).fetch(anyString());
    }

    @Test
    @DisplayName("Test parsing stops when a next page is unavailable")
    void givenUnavailableSecondPage_whenParse_thenFirstPageIsSaved() {
        //given
        Document firstPage = new Document(FIRST_PAGE_URL);
        List<FlatEntity> firstFlats = List.of(DataUtils.getFlatEntityTransient());
        given(client.fetch(FIRST_PAGE_URL)).willReturn(Optional.of(firstPage));
        given(client.fetch(SECOND_PAGE_URL)).willReturn(Optional.empty());
        given(parser.parse(firstPage, OfferType.RENT)).willReturn(new ListingPage(firstFlats, SECOND_PAGE_URL));
        //when
        serviceUnderTest.parse(SOURCE);
        //then
        verify(flatsRepository, times(1)).saveAll(firstFlats);
        verify(parser, times(1)).parse(any(), any());
    }
}
