package by.shershen.audit_service.repository;

import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("audit-test")
public class AuditRepositoryTests {

    @Autowired
    private AuditRepository auditRepository;

    @BeforeEach
    public void setUp() {
        auditRepository.deleteAll();
    }

    @Test
    @DisplayName("Test audit save functionality")
    public void givenAuditEntity_whenSave_thenRepositoryIsCalled() {
        //given
        AuditEntity auditEntity = DataUtils.getAuditEntityTransient();
        //when
        AuditEntity savedAudit = auditRepository.save(auditEntity);
        //then
        assertThat(savedAudit).isNotNull();
        assertThat(savedAudit.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test audit find by id functionality")
    public void givenAuditEntity_whenFindById_thenRepositoryIsCalled() {
        //given
        AuditEntity auditEntity = DataUtils.getAuditEntityTransient();
        AuditEntity savedAudit = auditRepository.save(auditEntity);
        //when
        AuditEntity obtainedAudit = auditRepository.findById(savedAudit.getId()).orElse(null);
        //then
        assertThat(obtainedAudit).isNotNull();
        assertThat(obtainedAudit.getId()).isEqualTo(savedAudit.getId());
    }

    @Test
    @DisplayName("Test find all audits functionality")
    public void givenListOfAuditEntities_whenFindAll_thenRepositoryIsCalled() {
        //given
        List<AuditEntity> auditList = List.of(DataUtils.getAuditEntityTransient(), DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());
        auditRepository.saveAll(auditList);
        //when
        List<AuditEntity> obtainedAudits = auditRepository.findAll();
        //then
        assertThat(obtainedAudits).isNotNull();
        assertThat(obtainedAudits.size()).isEqualTo(auditList.size());
    }
}
