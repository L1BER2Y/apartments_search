package by.shershen.user_service.controller;

import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IPageConverter;
import by.shershen.user_service.core.dto.UserCreateDTO;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.service.api.IAuthorizationService;
import by.shershen.user_service.service.api.IUserService;
import by.shershen.user_service.service.api.IVerificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AdminRestController.class)
@WithMockUser
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
    @DisplayName("Test createUser developer")
    public void givenUserCreateDto_whenCreateUser_thenReturnStatusIsCreated() throws Exception {
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
    @DisplayName("Test createUser developer with existing id")
    @Disabled
    public void givenUserCreateDto_whenCreateUserUser_thenErrorResponse() throws Exception {
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
    public void givenPageParams_whenGetPage_thenReturnPagination() throws Exception {
        //given
        UserDTO dto = new UserDTO(UUID.randomUUID(), 1L, 2L, "test@mail.com", "test", Role.USER, Status.WAITING_ACTIVATION);
        UserDTO dto1 = new UserDTO(UUID.randomUUID(), 2L, 3L, "test1@mail.com", "test1", Role.USER, Status.WAITING_ACTIVATION);
        List<UserDTO> list = List.of(dto, dto1);
        int number = 1;
        int size = 20;

        Pageable pageable = PageRequest.of(number, size);

        PageImpl<UserDTO> userDTOPage = new PageImpl<>(list, pageable, list.size());

        BDDMockito.given(userService.getPage(pageable))
                .willReturn(userDTOPage);
        //when
        ResultActions result = mockMvc.perform(get("/users?page=1&size=20")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(userService, times(1)).getPage(any(Pageable.class));
    }

    @Test
    @DisplayName("Test findInfo user by id")
    public void givenUserId_whenFindUserById_thenReturnUser() throws Exception {
        //given
        UUID id = UUID.randomUUID();

        UserDTO dto = new UserDTO(id, 1L, 2L, "test@mail.com", "test", Role.USER, Status.WAITING_ACTIVATION);

        BDDMockito.given(userService.findById(id))
                .willReturn(dto);
        //when
        ResultActions result = mockMvc.perform(get("/users/{uuid}", id)
                .with(csrf()));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uuid").value(id.toString()));
    }

    @Test
    @DisplayName("Test findInfo user with incorrect id")
    public void givenWrongUserId_whenFindUserById_thenReturnError() throws Exception {
        //given
        UUID id = UUID.randomUUID();

        BDDMockito.given(userService.findById(id))
                .willThrow(new ValidationException("No user found by this id"));
        //when
        ResultActions result = mockMvc.perform(get("/users/{uuid}", id)
                .with(csrf()));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message", CoreMatchers.is("No user found by this id")));
    }

    @Test
    @DisplayName("Test update user")
    public void givenUserUpdateDto_whenUpdateUser_thenReturnSuccessMessage() throws Exception {
        //given
        UserCreateDTO dto = UserCreateDTO.builder()
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();

        UserEntity userEntity = new UserEntity();
        userEntity.setDtCreate(LocalDateTime.now());
        userEntity.setDtUpdate(userEntity.getDtCreate());
        userEntity.setRole(dto.getRole());
        userEntity.setPassword(dto.getPassword());
        userEntity.setStatus(dto.getStatus());
        userEntity.setId(UUID.randomUUID());
        userEntity.setFio(dto.getFio());
        userEntity.setMail(dto.getMail());

        UUID uuid = userEntity.getId();
        LocalDateTime dtUpdate = LocalDateTime.now();

        BDDMockito.given(userService.update(userEntity, uuid, dtUpdate))
                .willReturn(userEntity);
        //when
        ResultActions result = mockMvc.perform(put("/users/{uuid}/dt_update/{dt_update}", uuid, dtUpdate)
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));
        //then
        result
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().bytes("User updated".getBytes()));
    }
}
