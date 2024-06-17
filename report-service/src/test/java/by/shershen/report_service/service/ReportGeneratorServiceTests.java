package by.shershen.report_service.service;

import by.shershen.report_service.core.entity.AuditEntity;
import by.shershen.report_service.util.DataUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ReportGeneratorServiceTests {

    @InjectMocks
    private ReportGeneratorService serviceUnderTest;

    @SneakyThrows
    @Test
    @DisplayName("Test report generation functionality")
    public void givenListOfAuditsAndID_whenGenerateReport_thenGenerateReport() {
        //given
        List<AuditEntity> listOfAudits = List.of(DataUtils.getAuditEntityTransient(), DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());
        UUID uuid = UUID.randomUUID();
        //when
        serviceUnderTest.generate(listOfAudits, uuid);
        //then
    }
}
