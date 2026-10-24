package by.shershen.user_service.service;

import by.shershen.user_service.clients.MailFeignClient;
import by.shershen.user_service.core.converters.api.IVerificationConverter;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.repository.VerificationRepository;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SendVerificationServiceTests {
    @Mock
    private VerificationRepository verificationRepository;

    @Mock
    private MailFeignClient mailClient;

    @Mock
    private IVerificationConverter verificationConverter;

    @InjectMocks
    private SendVerificationService serviceUnderTest;

    @Test
    @DisplayName("Test add User to verification queue functionality")
    void givenVerificationEntity_whenAddUserToVerificationQueue_thenRepositoryIsCalled() {
        //given
        UserEntity userEntity = DataUtils.getUserPersisted();
        //when
        serviceUnderTest.add(userEntity);
        //then
        verify(verificationRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test add User to verification queue functionality")
    void givenVerificationEntity_whenAddUserToVerificationQueue_thenExceptionIsThrown() {
        //given
        UserEntity userEntity = DataUtils.getUserPersisted();
        BDDMockito.given(verificationRepository.save(any(VerificationEntity.class)))
                .willThrow(new DataIntegrityViolationException("duplicate key"));
        //when
        assertThrows(DataIntegrityViolationException.class, () -> serviceUnderTest.add(userEntity));
        //then
    }

    @Test
    @DisplayName("Test send verification code functionality")
    void givenVerificationEntity_whenSendVerificationCode_thenRepositoryIsCalled() {
        //given
        BDDMockito.given(verificationRepository.findFirstBySendCodeFalse())
                .willReturn(Optional.of(DataUtils.getVerificationEntityTransient()));
        //when
        serviceUnderTest.sendCode();
        //then
        verify(verificationRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Test send verification code with empty queue")
    void givenNoVerificationEntity_whenSendVerificationCode_thenNothingIsSent() {
        //given
        BDDMockito.given(verificationRepository.findFirstBySendCodeFalse())
                .willReturn(Optional.empty());
        //when
        serviceUnderTest.sendCode();
        //then
        verify(mailClient, never()).send(any());
        verify(verificationRepository, never()).save(any());
    }
}
