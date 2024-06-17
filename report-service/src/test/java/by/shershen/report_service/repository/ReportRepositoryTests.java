package by.shershen.report_service.repository;

import by.shershen.report_service.core.entity.ReportEntity;
import by.shershen.report_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ReportRepositoryTests {

    @Autowired
    private ReportRepository reportRepository;

    @BeforeEach
    public void setUp() {
        reportRepository.deleteAll();
    }

    @Test
    @DisplayName("Test exportReport report functionality")
    public void givenReportEntity_whenSaveReport_thenReportIsSaved() {
        //given
        ReportEntity reportEntity = DataUtils.getReportEntityTransient();
        //when
        ReportEntity savedReport = reportRepository.saveAndFlush(reportEntity);
        //then
        assertThat(savedReport).isNotNull();
        assertThat(savedReport.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test find all reports functionality")
    public void givenThreeReportEntitiesStored_whenFindAllReports_thenReportsAreReturned() {
        //given
        ReportEntity entity = DataUtils.getReportEntityTransient();
        ReportEntity entity1 = DataUtils.getReportEntityOneTransient();
        ReportEntity entity2 = DataUtils.getReportEntityTwoTransient();

        reportRepository.saveAll(List.of(entity, entity1, entity2));
        //when
        List<ReportEntity> reportEntities = reportRepository.findAll();
        //then
        assertThat(CollectionUtils.isEmpty(reportEntities)).isNotNull();
    }

    @Test
    @DisplayName("Test get report status by id functionality")
    public void givenReportId_whenGetReportStatusById_thenStatusIsReturned() {
        //given
        ReportEntity reportEntity = DataUtils.getReportEntityTransient();
        reportRepository.saveAndFlush(reportEntity);
        UUID entityId = reportEntity.getId();
        //when
        String statusById = reportRepository.getStatusById(entityId);
        //then
        assertThat(statusById).isNotNull();
    }
}
