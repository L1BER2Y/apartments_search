package by.shershen.user_service.repository;

import by.shershen.user_service.core.entity.VerificationEntity;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class VerificationRepositoryTests {

    @Autowired
    private VerificationRepository verificationRepository;

    @BeforeEach
    void setUp() {
        verificationRepository.deleteAll();
    }

    @Test
    @DisplayName("Test find verification entity")
    void givenCodeAndMail_whenFindVerificationEntity_thenReturnVerificationEntity() {
        //given
        VerificationEntity verificationEntity = DataUtils.getVerificationEntityTransient();
        verificationRepository.save(verificationEntity);
        String code = verificationEntity.getCode();
        String mail = verificationEntity.getMail();
        //when
        Optional<VerificationEntity> foundEntity = verificationRepository.findByCodeAndMail(code, mail);
        //then
        assertThat(foundEntity).isPresent();
        assertThat(foundEntity.get().getCode()).isEqualTo(code);
        assertThat(foundEntity.get().getMail()).isEqualTo(mail);
    }

    @Test
    @DisplayName("Test find first verification entity with sendCode field false")
    void whenFindFirstVerificationEntityWithSendCodeFieldFalse_thenReturnFirstVerificationEntity() {
        //given
        VerificationEntity verificationEntity = DataUtils.getVerificationEntityTransient();
        verificationEntity.setSendCode(false);
        verificationRepository.save(verificationEntity);
        //when
        Optional<VerificationEntity> firstBySendCodeFalse = verificationRepository.findFirstBySendCodeFalse();
        //then
        assertThat(firstBySendCodeFalse).isPresent();
    }

    @Test
    @DisplayName("Test email verification with other code")
    void givenCode_whenEmailVerificationWithOtherCode_thenReturnNothing() {
        //given
        String code = "code";
        String mail = "hack@mail.com";
        VerificationEntity verificationEntity = DataUtils.getVerificationEntityTransient();
        verificationRepository.save(verificationEntity);
        //when
        Optional<VerificationEntity> entityByCodeAndMail = verificationRepository.findByCodeAndMail(code, mail);
        //then
        assertThat(entityByCodeAndMail).isEmpty();
    }
}
