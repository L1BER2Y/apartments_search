package by.shershen.report_service.service;

import by.shershen.report_service.core.entity.AuditEntity;
import by.shershen.report_service.util.DataUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReportGeneratorServiceTests {

    private final ReportGeneratorService serviceUnderTest = new ReportGeneratorService();

    private final UUID reportId = UUID.randomUUID();

    private final Path reportFile = Path.of(reportId + ".xlsx");

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(reportFile);
    }

    @Test
    @DisplayName("Test report generation functionality")
    void givenListOfAuditsAndID_whenGenerateReport_thenExcelFileWithAuditsIsCreated() throws IOException {
        //given
        AuditEntity audit = DataUtils.getAuditEntityTransient();
        List<AuditEntity> listOfAudits = List.of(audit, DataUtils.getAuditEntityOneTransient(), DataUtils.getAuditEntityTwoTransient());
        //when
        serviceUnderTest.generate(listOfAudits, reportId);
        //then
        assertThat(reportFile).exists();
        try (InputStream file = Files.newInputStream(reportFile);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {
            Sheet sheet = workbook.getSheet("Report Data");
            assertThat(sheet).isNotNull();
            assertThat(sheet.getPhysicalNumberOfRows()).isEqualTo(listOfAudits.size() + 1);

            Row header = sheet.getRow(0);
            assertThat(header.getCell(0).getStringCellValue()).isEqualTo("uuid");
            assertThat(header.getCell(6).getStringCellValue()).isEqualTo("action");

            Row firstAudit = sheet.getRow(1);
            assertThat(firstAudit.getCell(3).getStringCellValue()).isEqualTo(audit.getMail());
            assertThat(firstAudit.getCell(5).getStringCellValue()).isEqualTo(audit.getRole().toString());
            assertThat(firstAudit.getCell(6).getStringCellValue()).isEqualTo(audit.getText().toString());
        }
    }
}
