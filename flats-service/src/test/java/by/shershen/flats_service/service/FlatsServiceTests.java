package by.shershen.flats_service.service;

import by.shershen.flats_service.core.converters.api.IFlatConverter;
import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.FlatsFilter;
import by.shershen.flats_service.core.entity.FlatEntity;
import by.shershen.flats_service.repository.FlatsRepository;
import by.shershen.flats_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class FlatsServiceTests {

    @Mock
    private FlatsRepository flatsRepository;

    @Mock
    private IFlatConverter flatConverter;

    @InjectMocks
    private FlatsService serviceUnderTest;

    @Test
    @DisplayName("Test get page of flats functionality")
    public void givenFilterAndPageable_whenGetPageOfFlats_thenReturnPageOfFlats() {
        //given
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        List<FlatEntity> listOfFlatEntities = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityOneTransient(), DataUtils.getFlatEntityTwoTransient());

        FlatsFilter flatsFilter = DataUtils.getFlatsFilterTransient();

        BDDMockito.given(flatsRepository.findAll(any(Specification.class), any(Pageable.class)))
                .willReturn(new PageImpl<>(listOfFlatEntities, pageable, size));
        //when
        Page<FlatDTO> obtainedFlats = serviceUnderTest.getPage(flatsFilter, pageable);
        //then
        assertThat(obtainedFlats).isNotNull();
    }
}
