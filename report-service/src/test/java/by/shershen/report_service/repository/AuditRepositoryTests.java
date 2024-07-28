package by.shershen.report_service.repository;

import by.shershen.report_service.core.entity.AuditEntity;
import by.shershen.report_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("report-test")
public class AuditRepositoryTests {

    @Autowired
    private AuditRepository auditRepository;

    @BeforeEach
    public void setUp() {
        auditRepository.deleteAll();
    }

    @Test
    @DisplayName("Test find all audits with parameters functionality")
    public void givenParameters_whenFindAllAudits_thenListOfAuditsIsCreated() {
        //given
        AuditEntity audit = DataUtils.getAuditEntityTransient();
        AuditEntity audit1 = DataUtils.getAuditEntityOneTransient();
        AuditEntity audit2 = DataUtils.getAuditEntityTwoTransient();

        auditRepository.saveAll(List.of(audit, audit1, audit2));
        //when
        List<AuditEntity> obtainedAudits = auditRepository.findAllByParam(audit.getUuid(), audit.getDtCreate(), audit2.getDtCreate());
        //then
        assertThat(CollectionUtils.isEmpty(obtainedAudits)).isFalse();
    }
}
