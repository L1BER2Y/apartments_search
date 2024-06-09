package by.shershen.user_service.controller;

import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserRegDTO;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.service.api.IAuthorizationService;
import by.shershen.user_service.service.api.IUserService;
import by.shershen.user_service.service.api.IVerificationService;
import by.shershen.user_service.util.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserRestController.class)
@WithMockUser
public class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IUserService userService;

    @MockBean
    private IAuthorizationService authorizationService;

    @MockBean
    private JwtTokenHandler jwtTokenHandler;

    @MockBean
    private IVerificationService verificationService;

    @MockBean
    private IUserConverter userConverter;

    @Test
    @DisplayName("Test user registration functionality")
    public void givenUserRegDTO_whenRegisterUser_thenReturnStatusCreated() throws Exception {
        //given
        UserRegDTO userRegDTO = DataUtils.getUserRegDTOTransient();
        UserEntity userEntity = DataUtils.getUserPersisted();
        BDDMockito.given(userConverter.convertFromRegDTOToEntity(any(UserRegDTO.class)))
                .willReturn(userEntity);
        BDDMockito.given(userService.register(any(UserEntity.class)))
                .willReturn(userEntity);
        //when
        ResultActions result = mockMvc.perform(post("/users/registration")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegDTO)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Test user registration functionality")
    public void givenUserRegDTOWithExistingMail_whenRegisterUser_thenReturnStatusBadRequest() throws Exception {
        //given
        UserRegDTO userRegDTO = DataUtils.getUserRegDTOTransient();
        UserEntity userEntity = DataUtils.getUserPersisted();
        BDDMockito.given(userConverter.convertFromRegDTOToEntity(any(UserRegDTO.class)))
                .willReturn(userEntity);
        BDDMockito.given(userService.register(any(UserEntity.class)))
                .willThrow(ValidationException.class);
        //when
        ResultActions result = mockMvc.perform(post("/users/registration")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRegDTO)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @DisplayName("Test user verification functionality")
    public void givenMailAndCode_whenVerifyUser_thenReturnStatusOk() throws Exception {
        //given
        String mail = "test@test.com";
        String code = "code";
        VerificationDTO verificationDTO = new VerificationDTO(mail, code);
        Mockito.doNothing().when(verificationService).verify(verificationDTO);
        //when
        ResultActions result = mockMvc.perform(get("/users/verification")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
