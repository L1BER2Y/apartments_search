package by.shershen.user_service.service;

import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.dto.UserLoginDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorizationServiceTests {

    private static final String INVALID_CREDENTIALS = "Wrong mail or password";

    private static final String UNVERIFIED = "You have not passed verification or your account has been deactivated.";

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenHandler jwtTokenHandler;

    @Mock
    private IUserConverter userConverter;

    @InjectMocks
    private AuthorizationService authorizationService;

    @Test
    @DisplayName("Test user authorization")
    void givenUserLoginDTO_whenLogin_thenRepositoryIsCalled() {
        //given
        String token = "token";
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER);

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(true);

        BDDMockito.given(userRepository.findIdFioAndRoleByEmail(loginDTO.getMail()))
                .willReturn(Optional.of(userDetailsDTO));

        BDDMockito.given(userConverter.convertFromOptionalToDTO(any()))
                .willReturn(userDetailsDTO);

        BDDMockito.given(jwtTokenHandler.generateAccessToken(userDetailsDTO))
                .willReturn(token);

        //when
        String login = authorizationService.login(loginDTO);
        //then
        assertThat(login).isNotNull();
    }

    @Test
    @DisplayName("Test user authorization with wrong password")
    void givenWrongPassword_whenLogin_thenExceptionIsThrown() {
        //given
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(false);

        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> authorizationService.login(loginDTO));
        //then
        verify(userRepository, never()).findIdFioAndRoleByEmail(any());
        verify(jwtTokenHandler, never()).generateAccessToken(any());
        assertThat(validationException.getMessage()).isEqualTo(INVALID_CREDENTIALS);
    }

    @Test
    @DisplayName("Test when mail is not found")
    void givenMailIsNotFound_whenLogin_thenExceptionIsThrown() {
        //given
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.empty());
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> authorizationService.login(loginDTO));
        //then
        verify(passwordEncoder).matches(eq(loginDTO.getPassword()), any());
        verify(jwtTokenHandler, never()).generateAccessToken(any());
        assertThat(validationException.getMessage()).isEqualTo(INVALID_CREDENTIALS);
    }

    @Test
    @DisplayName("Test when account unverified and wrong password")
    void givenAccountUnverifiedAndWrongPassword_whenLogin_thenExceptionIsThrown() {
        //given
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUnverifiedUserPersisted()));

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(false);
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> authorizationService.login(loginDTO));
        //then
        assertThat(validationException.getMessage()).isEqualTo(INVALID_CREDENTIALS);
    }

    @Test
    @DisplayName("Test when account unverified but password is valid")
    void givenAccountUnverified_whenLogin_thenExceptionIsThrown() {
        //given
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();
        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUnverifiedUserPersisted()));

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(true);
        //when
        ValidationException validationException = assertThrows(ValidationException.class, () -> authorizationService.login(loginDTO));
        //then
        assertThat(validationException.getMessage()).isEqualTo(UNVERIFIED);
        verify(jwtTokenHandler, never()).generateAccessToken(any());
    }
}
