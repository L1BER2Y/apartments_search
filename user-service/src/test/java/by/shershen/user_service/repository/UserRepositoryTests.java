package by.shershen.user_service.repository;

import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.Status;
import by.shershen.user_service.core.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Test save user functionality")
    public void givenDeveloperEntity_whenSave_thenUserIsCreated() {
        //given
        UserEntity userToSave = UserEntity.builder()
                .id(UUID.randomUUID())
                .dtCreate(LocalDateTime.now())
                .dtUpdate(LocalDateTime.now())
                .mail("test@mail.com")
                .fio("Test")
                .role(Role.USER)
                .status(Status.ACTIVATED)
                .password("test")
                .build();
        //when
        UserEntity savedUser = userRepository.save(userToSave);
        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();

    }
}
