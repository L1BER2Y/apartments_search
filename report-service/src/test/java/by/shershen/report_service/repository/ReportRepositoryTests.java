package by.shershen.report_service.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ReportRepositoryTests {

    @Autowired
    private ReportRepository reportRepository;

    @BeforeEach
    public void setUp() {
        reportRepository.deleteAll();
    }

    @Test
    @DisplayName("Test")
}
