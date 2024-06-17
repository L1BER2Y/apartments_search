package by.shershen.parser_service.repository;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    @DisplayName("Test save all parsed flats")
    public void givenListOfFlats_whenSaveAll_thenRepositoryIsCalled() {
        //given
        List<FlatEntity> flatsList = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityOneTransient(), DataUtils.getFlatEntityTwoTransient());
        //when
        List<FlatEntity> obtainedFlats = flatsRepository.saveAll(flatsList);
        //then
        assertThat(obtainedFlats).isNotEmpty();
        assertThat(obtainedFlats.size()).isEqualTo(flatsList.size());
    }
}
