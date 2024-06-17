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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenHandler jwtTokenHandler;

    @Mock
    private IUserConverter userConverter;

    @InjectMocks
    private AuthorizationService serviceUnderTest;

    @Test
    @DisplayName("Test user authorization functionality")
    public void givenUserLoginDTO_whenLogin_thenRepositoryIsCalled() {
        //given
        String token = "token";
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER);

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));

        BDDMockito.given(userConverter.convertFromOptionalToEntity(any()))
                .willReturn(DataUtils.getUserPersisted());

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(true);

        BDDMockito.given(userRepository.findIdFioAndRoleByEmail(loginDTO.getMail()))
                .willReturn(Optional.of(userDetailsDTO));

        BDDMockito.given(userConverter.convertFromOptionalToDTO(any()))
                .willReturn(userDetailsDTO);

        BDDMockito.given(jwtTokenHandler.generateAccessToken(userDetailsDTO))
                .willReturn(token);

        //when
        String login = serviceUnderTest.login(loginDTO);
        //then
        assertThat(login).isNotNull();
    }

    @Test
    @DisplayName("Test user authorization with wrong credentials functionality")
    public void givenUserLoginDTOWithWrongCredentials_whenLogin_thenExceptionIsThrown() {
        //given
        UserLoginDTO loginDTO = DataUtils.getUserLoginDTOTransient();

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER);

        BDDMockito.given(userRepository.findByMail(loginDTO.getMail()))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));

        BDDMockito.given(userConverter.convertFromOptionalToEntity(any()))
                .willReturn(DataUtils.getUserPersisted());

        BDDMockito.given(passwordEncoder.matches(loginDTO.getPassword(), "test"))
                .willReturn(false);

        //when
        assertThrows(ValidationException.class, () -> serviceUnderTest.login(loginDTO));
        //then
        verify(userRepository, never()).findIdFioAndRoleByEmail(any());
        verify(jwtTokenHandler, never()).generateAccessToken(any());
    }
}
