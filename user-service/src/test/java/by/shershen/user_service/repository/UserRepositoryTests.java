package by.shershen.user_service.repository;

import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
        UserEntity userToSave = DataUtils.getUserTransient();
        //when
        UserEntity savedUser = userRepository.saveAndFlush(userToSave);
        //then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    @DisplayName("Test update user functionality")
    public void givenUserEntity_whenUpdate_thenEmailIsChanged() {
        //given
        String updatedEmail = "updated@mail.com";
        UserEntity userToSave = DataUtils.getUserTransient();
        userRepository.saveAndFlush(userToSave);
        //when
        UserEntity userToUpdate = userRepository.findById(userToSave.getId()).orElse(null);
        userToUpdate.setMail(updatedEmail);
        UserEntity updatedUser = userRepository.saveAndFlush(userToUpdate);
        //then
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getMail()).isEqualTo(updatedEmail);
    }

    @Test
    @DisplayName("Test get user by id functionality")
    public void givenUserId_whenGetById_thenUserIsReturned() {
        //given
        UserEntity userEntity = DataUtils.getUserTransient();
        UserEntity saved = userRepository.saveAndFlush(userEntity);
        //when
        UserEntity obtainedUser = userRepository.findById(saved.getId()).orElse(null);
        //then
        assertThat(obtainedUser).isNotNull();
        assertThat(obtainedUser.getMail()).isEqualTo(userEntity.getMail());
    }

    @Test
    @DisplayName("Test user not found functionality")
    public void givenUserIsNotCreated_whenGetById_thenOptionalIsEmpty() {
        //given

        //when
        UserEntity obtainedUser = userRepository.findById(UUID.randomUUID()).orElse(null);
        //then
        assertThat(obtainedUser).isNull();
    }

    @Test
    @DisplayName("Test get all developers functionality")
    public void givenThreeUsersAreStored_whenFindAll_thenAllUsersAreReturned() {
        //given
        UserEntity user1 = DataUtils.getUserTransient();
        UserEntity user2 = DataUtils.getUserOneTransient();
        UserEntity user3 = DataUtils.getUserTwoTransient();

        userRepository.saveAll(List.of(user1, user2, user3));
        //when
        List<UserEntity> obtainedUsers = userRepository.findAll();
        //then
        assertThat(CollectionUtils.isEmpty(obtainedUsers)).isFalse();
    }

    @Test
    @DisplayName("Test get user functionality")
    public void givenUserSaved_whenGetByEmail_thenUserIsReturned() {
        //given
        UserEntity user = DataUtils.getUserTransient();
        userRepository.saveAndFlush(user);
        //when
        UserEntity obtainedUser = userRepository.findByMail(user.getMail()).orElse(null);
        //then
        assertThat(obtainedUser).isNotNull();
        assertThat(obtainedUser.getMail()).isEqualTo(user.getMail());
    }

    @Test
    @DisplayName("Test get user functionality")
    public void givenUserSaved_whenGetIdFioAndRoleByEmail_thenUserIsReturned() {
        //given
        UserEntity user = DataUtils.getUserTransient();
        userRepository.saveAndFlush(user);
        //when
        UserDetailsDTO obtainedUser = userRepository.findIdFioAndRoleByEmail(user.getMail()).orElse(null);
        //then
        assertThat(obtainedUser).isNotNull();
        assertThat(obtainedUser.getMail()).isEqualTo(user.getMail());
        assertThat(obtainedUser.getFio()).isEqualTo(user.getFio());
        assertThat(obtainedUser.getId()).isEqualTo(user.getId());
        assertThat(obtainedUser.getRole()).isEqualTo(user.getRole());
    }

    @Test
    @DisplayName("Test get user functionality")
    public void givenUserSaved_whenExistsByMail_thenTrue() {
        //given
        UserEntity user = DataUtils.getUserTransient();
        userRepository.saveAndFlush(user);
        //when
        Boolean userExists = userRepository.existsByMail(user.getMail());
        //then
        assertThat(userExists).isNotNull();
        assertThat(userExists).isTrue();
    }

    @Test
    @DisplayName("Test delete user functionality")
    public void givenUserIsSaved_whenDeleteById_thenUserIsRemovedFromDB() {
        //given
        UserEntity user = DataUtils.getUserTransient();
        userRepository.saveAndFlush(user);
        //when
        userRepository.deleteById(user.getId());
        //then
        UserEntity obtainedUser = userRepository.findById(user.getId()).orElse(null);
        assertThat(obtainedUser).isNull();
    }
}
