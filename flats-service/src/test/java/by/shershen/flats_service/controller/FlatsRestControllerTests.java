package by.shershen.flats_service.controller;

import by.shershen.flats_service.core.converters.api.IPageConverter;
import by.shershen.flats_service.core.dto.FlatDTO;
import by.shershen.flats_service.core.dto.FlatsFilter;
import by.shershen.flats_service.core.dto.PageOfFlatDTO;
import by.shershen.flats_service.service.api.IFlatsService;
import by.shershen.flats_service.util.DataUtils;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(FlatsRestController.class)
public class FlatsRestControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IFlatsService flatsService;

    @MockBean
    private IPageConverter pageConverter;

    @Test
    @DisplayName("Test get flats with filters and pagination functionality")
    public void givenFiltersAndPagination_whenGetFlats_thenReturnFlats() throws Exception {
        //given
        int page = 1;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        List<FlatDTO> flatDTOList = List.of(DataUtils.getFlatDTOTransient(), DataUtils.getFlatDTOOneTransient(), DataUtils.getFlatDTOTwoTransient());
        FlatsFilter flatsFilter = DataUtils.getFlatsFilterTransient();

        BDDMockito.given(flatsService.getPage(flatsFilter, pageable))
                .willReturn(new PageImpl<>(flatDTOList, pageable, size));

        BDDMockito.given(pageConverter.convertPageToPageOfFlatDTO(any()))
                .willReturn(new PageOfFlatDTO<>(1, 10, 1, 13L, true, 3, true, flatDTOList));
        //when
        ResultActions result = mockMvc.perform(get("/flats")
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
