package by.shershen.report_service.service;

import by.shershen.report_service.core.converters.api.IReportConverter;
import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.AuditEntity;
import by.shershen.report_service.core.entity.ReportEntity;
import by.shershen.report_service.core.entity.Status;
import by.shershen.report_service.core.entity.Type;
import by.shershen.report_service.repository.AuditRepository;
import by.shershen.report_service.repository.ReportRepository;
import by.shershen.report_service.service.api.IReportGenerator;
import by.shershen.report_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTests {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private AuditRepository auditRepository;

    @Mock
    private IReportConverter reportConverter;

    @Mock
    private IReportGenerator reportGenerator;

    @InjectMocks
    private ReportService serviceUnderTest;

    @Test
    @DisplayName("Test create report functionality")
    public void givenTypeAndParams_whenCreateReport_thenRepositoryIsCalled() {
        //given
        Type type = Type.JOURNAL_AUDIT;
        UserActionAuditParamDTO paramDTO = DataUtils.getUserActionAuditParamDTOTransient();
        ReportEntity reportToSave = DataUtils.getReportEntityTransient();
        List<AuditEntity> listOfAudits = List.of(DataUtils.getAuditEntityTransient(), DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());

        BDDMockito.given(reportRepository.saveAndFlush(any(ReportEntity.class)))
                .willReturn(reportToSave);

        BDDMockito.given(auditRepository.findAllByParam(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(listOfAudits);
        //when
        serviceUnderTest.create(type, paramDTO);
        //then
        verify(reportRepository, times(2)).saveAndFlush(any(ReportEntity.class));
        verify(auditRepository, times(1)).findAllByParam(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class));
    }

    @Test
    @DisplayName("Test create report functionality")
    public void givenTypeAndParams_whenCreateReport_thenExceptionIsThrown() throws Exception {
        //given
        Type type = Type.JOURNAL_AUDIT;
        UserActionAuditParamDTO paramDTO = DataUtils.getUserActionAuditParamDTOTransient();
        ReportEntity reportToSave = DataUtils.getReportEntityTransient();
        List<AuditEntity> listOfAudits = List.of(DataUtils.getAuditEntityTransient(), DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());

        BDDMockito.given(reportRepository.saveAndFlush(any(ReportEntity.class)))
                .willReturn(reportToSave);

        BDDMockito.given(auditRepository.findAllByParam(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class)))
                .willReturn(listOfAudits);

        Mockito.doThrow(IOException.class).when(reportGenerator).generate(listOfAudits, reportToSave.getId());
        //when
        serviceUnderTest.create(type, paramDTO);
        //then
        verify(reportRepository, times(2)).saveAndFlush(any(ReportEntity.class));
        verify(auditRepository, times(1)).findAllByParam(any(UUID.class), any(LocalDateTime.class), any(LocalDateTime.class));
        verify(reportGenerator, times(1)).generate(any(), any());
        assertThat(reportToSave.getStatus()).isEqualTo(Status.ERROR);
    }

    @Test
    @DisplayName("Test get reports page functionality")
    public void givenPageParams_whenGetAllReports_thenRepositoryIsCalled() {
        //given
        List<ReportEntity> reportList = List.of(DataUtils.getReportEntityTransient(), DataUtils.getReportEntityOneTransient(), DataUtils.getReportEntityTwoTransient());
        int page = 1;
        int size = 20;
        Pageable pageable = PageRequest.of(page, size);

        BDDMockito.given(reportRepository.findAll(pageable))
                .willReturn(new PageImpl<>(reportList, pageable, size));
        //when
        Page<ReportDTO> testPage = serviceUnderTest.getAllReports(pageable);
        //then
        assertThat(testPage).isNotNull();
        assertThat(testPage.getNumberOfElements()).isEqualTo(reportList.size());
        verify(reportRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Test get report status by id functionality")
    public void givenReportId_whenGetStatusById_thenRepositoryIsCalled() {
        //given
        String status = "DONE";
        String id = UUID.randomUUID().toString();
        BDDMockito.given(reportRepository.getStatusById(any()))
                .willReturn(status);
        //when
        Status obtainedStatus = serviceUnderTest.getStatusById(id);
        //then
        verify(reportRepository, times(1)).getStatusById(any());
        assertThat(obtainedStatus.getReportStatusId()).isEqualTo(status);
    }
}
