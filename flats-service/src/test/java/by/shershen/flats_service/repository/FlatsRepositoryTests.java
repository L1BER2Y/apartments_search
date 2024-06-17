package by.shershen.flats_service.repository;

import by.shershen.flats_service.core.FlatSpecification;
import by.shershen.flats_service.core.dto.FlatsFilter;
import by.shershen.flats_service.core.entity.FlatEntity;
import by.shershen.flats_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class FlatsRepositoryTests {

    @Autowired
    private FlatsRepository flatsRepository;

    @BeforeEach
    public void setUp() {
        flatsRepository.deleteAll();
    }

    @Test
    @DisplayName("Test find all flats functionality")
    public void givenFlatsFilterAndPageable_whenFindAll_thenFlatsPageReturned() {
        //given
        Pageable pageable = PageRequest.of(1, 20);
        FlatsFilter flatsFilter = DataUtils.getFlatsFilterTransient();
        List<FlatEntity> flatEntityList = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityOneTransient(), DataUtils.getFlatEntityTwoTransient());
        flatsRepository.saveAll(flatEntityList);
        //when
        Page<FlatEntity> obtainedFlats = flatsRepository.findAll(FlatSpecification.byFlatsFilter(flatsFilter), pageable);
        //then
        assertThat(obtainedFlats).isNotNull();
        assertThat(obtainedFlats.getTotalElements()).isEqualTo(flatEntityList.size());
    }
}
