package by.shershen.report_service.controller;

import by.shershen.report_service.core.converters.api.IPageConverter;
import by.shershen.report_service.service.api.IReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(controllers = ReportRestController.class)
@ExtendWith(MockitoExtension.class)
public class ReportRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IReportService reportService;

    @MockBean
    private IPageConverter pageConverter;

    @Test
    @DisplayName("Test create report functionality")
    public void givenParamDtoAndReportType_whenCreateReport_thenReturnStatusCreated() throws Exception {
        //given

        //when

        //then
    }
}
