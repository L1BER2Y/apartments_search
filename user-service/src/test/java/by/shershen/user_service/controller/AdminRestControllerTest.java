package by.shershen.user_service.controller;

import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IPageConverter;
import by.shershen.user_service.core.dto.UserCreateDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.service.api.IAuthorizationService;
import by.shershen.user_service.service.api.IUserService;
import by.shershen.user_service.service.api.IVerificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(AdminRestController.class)
public class AdminRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserService userService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private IPageConverter pageConverter;

    @MockBean
    private JwtTokenHandler jwtTokenHandler;

    @MockBean
    private IAuthorizationService authorizationService;

    @MockBean
    private IVerificationService verificationService;

    @Test
    @WithMockUser
    @DisplayName("Test create developer functionality")
    public void givenUserCreateDto_whenCreateUser_thenCreateUser() throws Exception {
        //given
        UserCreateDTO dto = UserCreateDTO.builder()
                .fio("Test")
                .mail("test@test.com")
                .role(Role.ADMIN)
                .password("test")
                .status(Status.WAITING_ACTIVATION)
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setDtCreate(LocalDateTime.now());
        userEntity.setDtUpdate(LocalDateTime.now());
        userEntity.setRole(dto.getRole());
        userEntity.setPassword(dto.getPassword());
        userEntity.setStatus(dto.getStatus());
        userEntity.setId(UUID.randomUUID());
        userEntity.setFio(dto.getFio());
        userEntity.setMail(dto.getMail());

        BDDMockito.given(userService.save(any(UserEntity.class)))
                .willReturn(userEntity);
        //when
        ResultActions result = mockMvc.perform(post("/users")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @WithMockUser
    @DisplayName("Test create developer functionality")
    public void givenUserCreateDto_whenCreateUser_thenErrorResponse() throws Exception {
        //given
        UserCreateDTO dto = UserCreateDTO.builder()
                .fio("Test")
                .mail("test@test.com")
                .role(Role.ADMIN)
                .password("test")
                .status(Status.WAITING_ACTIVATION)
                .build();

        BDDMockito.given(userService.save(any(UserEntity.class)))
                .willThrow(new ValidationException("Validation Exception"));
        //when
        ResultActions result = mockMvc.perform(post("/users")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @DisplayName("Test get page with params")
    public void givenPageParams_whenGetPage_thenGetPage() throws Exception {

    }
}
