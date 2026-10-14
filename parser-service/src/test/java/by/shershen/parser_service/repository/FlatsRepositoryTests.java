package by.shershen.parser_service.repository;

import by.shershen.parser_service.core.entity.FlatEntity;
import by.shershen.parser_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@ActiveProfiles("parser-test")
class FlatsRepositoryTests {

    @Autowired
    private FlatsRepository flatsRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        flatsRepository.deleteAll();
    }

    @Test
    @DisplayName("Test save all parsed flats")
    void givenListOfFlats_whenSaveAll_thenRepositoryIsCalled() {
        //given
        List<FlatEntity> flatsList = List.of(DataUtils.getFlatEntityTransient(), DataUtils.getFlatEntityOneTransient(), DataUtils.getFlatEntityTwoTransient());
        //when
        List<FlatEntity> obtainedFlats = flatsRepository.saveAll(flatsList);
        //then
        assertThat(obtainedFlats).isNotEmpty().hasSameSizeAs(flatsList);
    }

    @Test
    @DisplayName("Test id and timestamps are generated on save")
    void givenNewFlat_whenSaveAndFlush_thenIdAndTimestampsAreGenerated() {
        //given
        FlatEntity flat = DataUtils.getFlatEntityTransient();
        //when
        FlatEntity savedFlat = flatsRepository.saveAndFlush(flat);
        //then
        assertThat(savedFlat.getUuid()).isNotNull();
        assertThat(savedFlat.getDtCreate()).isNotNull();
        assertThat(savedFlat.getDtUpdate()).isNotNull();
    }

    @Test
    @DisplayName("Test photo urls are stored as an array and read back")
    void givenFlatWithPhotos_whenSaveAndFindById_thenPhotoUrlsAreRestored() {
        //given
        FlatEntity flat = flatsRepository.saveAndFlush(DataUtils.getFlatEntityTransient());
        entityManager.clear();
        //when
        FlatEntity foundFlat = flatsRepository.findById(flat.getUuid()).orElseThrow();
        //then
        assertThat(foundFlat.getPhotoUrls()).containsExactly(flat.getPhotoUrls());
    }

    @Test
    @DisplayName("Test a flat with an already saved original url is rejected")
    void givenSavedFlat_whenSaveFlatWithSameOriginalUrl_thenExceptionIsThrown() {
        //given
        flatsRepository.saveAndFlush(DataUtils.getFlatEntityTransient());
        FlatEntity duplicate = DataUtils.getFlatEntityTransient();
        //when
        //then
        assertThatThrownBy(() -> flatsRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("Test only already saved original urls are found")
    void givenSavedFlat_whenFindExistingOriginalUrls_thenOnlySavedUrlIsReturned() {
        //given
        FlatEntity savedFlat = DataUtils.getFlatEntityTransient();
        flatsRepository.save(savedFlat);
        //when
        Set<String> existingUrls = flatsRepository.findExistingOriginalUrls(
                List.of(savedFlat.getOriginalUrl(), "New original url"));
        //then
        assertThat(existingUrls).containsExactly(savedFlat.getOriginalUrl());
    }
}
