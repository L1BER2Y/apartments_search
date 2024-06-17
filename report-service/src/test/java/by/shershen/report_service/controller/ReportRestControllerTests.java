package by.shershen.report_service.controller;

import by.shershen.report_service.controller.utils.JwtTokenHandler;
import by.shershen.report_service.core.converters.api.IPageConverter;
import by.shershen.report_service.core.dto.ReportDTO;
import by.shershen.report_service.core.dto.UserActionAuditParamDTO;
import by.shershen.report_service.core.entity.ReportEntity;
import by.shershen.report_service.core.entity.Status;
import by.shershen.report_service.core.entity.Type;
import by.shershen.report_service.service.api.IReportService;
import by.shershen.report_service.util.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WithMockUser
@WebMvcTest(controllers = ReportRestController.class)
@ExtendWith(MockitoExtension.class)
public class ReportRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenHandler jwtTokenHandler;

    @MockBean
    private IReportService reportService;

    @MockBean
    private IPageConverter pageConverter;

    @Test
    @DisplayName("Test create report functionality")
    public void givenParamDtoAndReportType_whenCreateReport_thenReturnStatusCreated() throws Exception {
        //given
        UserActionAuditParamDTO paramDTO = DataUtils.getUserActionAuditParamDTOTransient();
        Type type = Type.JOURNAL_AUDIT;
        reportService.create(type, paramDTO);
        //when
        ResultActions result = mockMvc.perform(post("/report/{type}", type)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(type))
                .content(objectMapper.writeValueAsString(paramDTO)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("Test get reports page functionality")
    public void givenPageParams_whenGetReportPage_thenReturnPage() throws Exception {
        //given
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        List<ReportDTO> reportDTOList = List.of(DataUtils.getReportDtoTransient(), DataUtils.getReportDtoOneTransient(), DataUtils.getReportDtoTwoTransient());
        PageImpl<ReportDTO> reportDTOPage = new PageImpl<>(reportDTOList, pageable, reportDTOList.size());

        BDDMockito.given(reportService.getAllReports(pageable))
                .willReturn(reportDTOPage);
        //when
        ResultActions result = mockMvc.perform(get("/report")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(reportService, times(1)).getAllReports(any());
    }

    @Test
    @DisplayName("Test report export functionality")
    public void givenUUID_whenExportReport_thenReturnReport() throws Exception {
        //given
        UUID uuid = UUID.randomUUID();
        String reportLink = "test link";
        BDDMockito.given(reportService.exportReport(uuid))
                .willReturn(new ResponseEntity<>(reportLink, HttpStatus.OK));
        //when
        ResultActions result = mockMvc.perform(get("/report/{uuid}/export", uuid)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(uuid)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test get report status functionality")
    public void givenReportId_whenGetReportStatus_thenReturnReportStatus() throws Exception {
        //given
        String id = "test id";
        BDDMockito.given(reportService.getStatusById(id))
                .willReturn(Status.DONE);
        //when
        ResultActions result = mockMvc.perform(head("/report/{id}/export", id)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(id)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
