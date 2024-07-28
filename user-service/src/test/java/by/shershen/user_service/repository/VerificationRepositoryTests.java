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
public class VerificationRepositoryTests {

    @Autowired
    private VerificationRepository verificationRepository;

    @BeforeEach
    public void setUp() {
        verificationRepository.deleteAll();
    }

    @Test
    @DisplayName("Test find verification entity by code functionality")
    public void givenCode_whenFindVerificationEntityByCode_thenReturnVerificationEntity() {
        //given
        String code = "code";
        VerificationEntity verificationEntity = DataUtils.getVerificationEntityTransient();
        verificationRepository.save(verificationEntity);
        //when
        Optional<VerificationEntity> verificationEntitiesByCode = verificationRepository.findVerificationEntitiesByCode(code);
        //then
        assertThat(verificationEntitiesByCode).isPresent();
        assertThat(verificationEntitiesByCode.get().getCode()).isEqualTo(code);
    }

    @Test
    @DisplayName("Test find first verification entity with sendCode field false")
    public void whenFindFirstVerificationEntityWithSendCodeFieldFalse_thenReturnFirstVerificationEntity() {
        //given
        String code = "code";
        VerificationEntity verificationEntity = DataUtils.getVerificationEntityTransient();
        verificationEntity.setSendCode(false);
        verificationRepository.save(verificationEntity);
        //when
        Optional<VerificationEntity> firstBySendCodeFalse = verificationRepository.findFirstBySendCodeFalse();
        //then
        assertThat(firstBySendCodeFalse).isPresent();
    }
}
