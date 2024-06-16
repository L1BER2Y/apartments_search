package by.shershen.audit_service.controller;

import by.shershen.audit_service.controller.utils.JwtTokenHandler;
import by.shershen.audit_service.core.converters.api.IAuditConverter;
import by.shershen.audit_service.core.converters.api.IPageConverter;
import by.shershen.audit_service.core.dto.AuditDTO;
import by.shershen.audit_service.core.dto.AuditInfoDTO;
import by.shershen.audit_service.core.dto.PageOfAuditDTO;
import by.shershen.audit_service.core.entity.AuditEntity;
import by.shershen.audit_service.service.api.IAuditService;
import by.shershen.audit_service.util.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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

@WebMvcTest
@WithMockUser
public class AuditRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtTokenHandler jwtTokenHandler;

    @MockBean
    private IAuditConverter auditConverter;

    @MockBean
    private IAuditService auditService;

    @MockBean
    private IPageConverter pageConverter;

    @Test
    @DisplayName("Test get page of audit functionality")
    public void givenPageParams_whenGetAudit_thenReturnPage() throws Exception {
        //given
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        List<AuditInfoDTO> listOfAudits = List.of(DataUtils.getAuditInfoDTOTransient(), DataUtils.getAuditInfoDTOOneTransient(), DataUtils.getAuditInfoDTOTwoTransient());

        BDDMockito.given(auditService.getAudit(pageable))
                .willReturn(new PageImpl<>(listOfAudits, pageable, listOfAudits.size()));

        BDDMockito.given(pageConverter.convertPageToPageOfAuditDTO(any(PageImpl.class)))
                .willReturn(new PageOfAuditDTO<>(1, 10, 1, 3L, true, 3, true, listOfAudits));
        //when
        ResultActions result = mockMvc.perform(get("/audit")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
                verify(auditService, times(1)).getAudit(any(Pageable.class));
    }

    @Test
    @DisplayName("Test get audit by id functionality")
    public void givenAuditId_whenGetAudit_thenReturnAuditInfoDTO() throws Exception {
        //given
        UUID id = UUID.randomUUID();
        AuditInfoDTO auditInfoDTO = DataUtils.getAuditInfoDTOTransient();
        UUID auditInfoDTOUuid = auditInfoDTO.getUuid();
        BDDMockito.given(auditService.getAuditById(id))
                .willReturn(auditInfoDTO);
        //when
        ResultActions result = mockMvc.perform(get("/audit/{uuid}", id)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(auditInfoDTOUuid.toString()));
    }

    @Test
    @DisplayName("Test save request acceptance functionality")
    public void givenAuditDtoToSave_whenSaveAudit_thenReturnAuditInfoDTO() throws Exception {
        //given
        String AUTHORIZATION = "Authorization";
        AuditDTO auditDTO = DataUtils.getAuditDTOTransient();

        BDDMockito.given(auditService.saveAudit(any(AuditDTO.class)))
                .willReturn(DataUtils.getAuditEntityTransient());

        BDDMockito.given(auditConverter.convertAuditEntityToAuditDTO(any(AuditEntity.class)))
                .willReturn(DataUtils.getAuditDTOTransient());
        //when
        ResultActions result = mockMvc.perform(post("/audit")
                .with(csrf())
                .header(AUTHORIZATION, "Authorization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(auditDTO)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
