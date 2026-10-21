package by.shershen.user_service.service;

import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.dto.VerificationDTO;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.repository.VerificationRepository;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class VerificationServiceTests {
    @Mock
    private VerificationRepository verificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private IUserConverter userConverter;

    @Mock
    private IVerificationConverter verificationConverter;

    @InjectMocks
    private VerificationService verificationService;

    @Test
    @DisplayName("Test user verification functionality")
    void givenVerificationDTO_whenVerify_thenRepositoryIsCalled() {
        //given
        VerificationDTO verificationDTO = new VerificationDTO("code", "test@mail.com");

        BDDMockito.given(verificationRepository.findByCodeAndMail(any(), any()))
                .willReturn(Optional.of(DataUtils.getVerificationEntityTransient()));

        BDDMockito.given(verificationConverter.convertFromOptionalToEntity(any()))
                        .willReturn(DataUtils.getVerificationEntityTransient());

        BDDMockito.given(userRepository.findByMail(any()))
                .willReturn(Optional.of(DataUtils.getUserTransient()));

        BDDMockito.given(userConverter.convertFromOptionalToEntity(any()))
                .willReturn(DataUtils.getUserTransient());
        //when
        verificationService.verify(verificationDTO);
        //then
        verify(verificationRepository, times(1)).findByCodeAndMail("code", "test@mail.com");
        verify(userRepository, times(1)).findByMail("test@mail.com");
        verify(userConverter, times(1)).convertFromOptionalToEntity(any());
        verify(userRepository, times(1)).saveAndFlush(any());
        verify(verificationRepository, times(1)).delete(any());
    }

    @Test
    @DisplayName("Test verified user functionality")
    void givenVerificationDTOVerified_whenVerify_thenExceptionIsThrown() {
        //given
        VerificationDTO verificationDTO = new VerificationDTO("code", "test@mail.com");

        BDDMockito.given(verificationRepository.findByCodeAndMail(any(), any()))
                .willReturn(Optional.empty());
        //when
        assertThrows(ValidationException.class, () -> verificationService.verify(verificationDTO));
        //then
        verify(userRepository, never()).saveAndFlush(any());
    }
}
