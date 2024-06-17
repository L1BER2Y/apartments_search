package by.shershen.audit_service.service;

import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.repository.AuditRepository;
import by.shershen.audit_service.util.DataUtils;
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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditServiceTests {

    @Mock
    private IAuditConverter auditConverter;

    @Mock
    private AuditRepository auditRepository;

    @InjectMocks
    private AuditService serviceUnderTest;

    @Test
    @DisplayName("Test get audit page functionality")
    public void givenPageParameters_whenGetAudit_thenRepositoryIsCalled() {
        //given
        List<AuditEntity> listOfAudits = List.of(DataUtils.getAuditEntityTransient(), DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        BDDMockito.given(auditRepository.findAll(pageable))
                .willReturn(new PageImpl<>(listOfAudits, pageable, size));
        //when
        Page<AuditInfoDTO> auditPage = serviceUnderTest.getAudit(pageable);
        //then
        assertThat(auditPage).isNotNull();
        assertThat(auditPage.getNumberOfElements()).isEqualTo(listOfAudits.size());
        verify(auditRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Test get audit by id functionality")
    public void givenAuditId_whenGetAuditById_thenRepositoryIsCalled() {
        //given
        BDDMockito.given(auditRepository.findById(any(UUID.class)))
                .willReturn(Optional.of(DataUtils.getAuditEntityTransient()));
        Mockito.when(auditConverter.convertAuditOptionalToEntity(any(Optional.class)))
                .thenReturn(DataUtils.getAuditEntityOneTransient());
        Mockito.when(auditConverter.convertAuditEntityToInfoDTO(any(AuditEntity.class)))
                .thenReturn(DataUtils.getAuditInfoDTOTransient());
        //when
        AuditInfoDTO obtainedAudit = serviceUnderTest.getAuditById(UUID.randomUUID());
        //then
        assertThat(obtainedAudit).isNotNull();
    }

    @Test
    @DisplayName("Test save audit functionality")
    public void givenAuditDTO_whenSaveAudit_thenRepositoryIsCalled() {
        //given
        AuditDTO auditDTO = DataUtils.getAuditDTOTransient();
        BDDMockito.given(auditConverter.convertAuditDTOToAuditEntity(any(AuditDTO.class)))
                .willReturn(DataUtils.getAuditEntityTransient());
        BDDMockito.given(auditRepository.save(any(AuditEntity.class)))
                .willReturn(DataUtils.getAuditEntityOneTransient());
        //when
        AuditEntity obtainedAudit = serviceUnderTest.saveAudit(auditDTO);
        //then
        assertThat(obtainedAudit).isNotNull();
    }
}
