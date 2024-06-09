package by.shershen.user_service.service;

import by.shershen.user_service.controller.utils.JwtTokenHandler;
import by.shershen.user_service.core.converters.api.IUserConverter;
import by.shershen.user_service.core.dto.UserDTO;
import by.shershen.user_service.core.dto.UserDetailsDTO;
import by.shershen.user_service.core.entity.Role;
import by.shershen.user_service.core.entity.UserEntity;
import by.shershen.user_service.core.exceptions.EntityNotFoundException;
import by.shershen.user_service.core.exceptions.ValidationException;
import by.shershen.user_service.repository.UserRepository;
import by.shershen.user_service.service.api.IVerificationQueueService;
import by.shershen.user_service.util.DataUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTests {

    @Mock
    private IVerificationQueueService verificationQueueService;

    @Mock
    private IUserConverter userConverter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService serviceUnderTest;

    @Test
    @DisplayName("Test save user functionality")
    public void givenUserToSave_whenSaveUser_thenRepositoryIsCalled() {
        //given
        UserEntity userToSave = DataUtils.getUserTransient();
        BDDMockito.given(userRepository.findByMail(anyString()))
                .willReturn(null);
        BDDMockito.given(userRepository.saveAndFlush(any(UserEntity.class)))
                .willReturn(DataUtils.getUserPersisted());
        //when
        UserEntity savedUser = serviceUnderTest.save(userToSave);
        //then
        assertThat(savedUser).isNotNull();
    }

    @Test
    @DisplayName("Test save user with duplicate email functionality")
    public void givenUserToSaveWithDuplicatedEmail_whenSaveUser_thenExceptionIsThrown() {
        //given
        UserEntity userToSave = DataUtils.getUserTransient();
        BDDMockito.given(userRepository.existsByMail(anyString()))
                .willReturn(true);
        //when
        assertThrows(ValidationException.class, () -> serviceUnderTest.save(userToSave));
        //then
        verify(userRepository, never()).saveAndFlush(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test update user functionality")
    public void givenUserToUpdate_whenUpdateUser_thenRepositoryIsCalled() {
        //given
        UserEntity userToUpdate = DataUtils.getUserTransient();
        UUID id = userToUpdate.getId();
        LocalDateTime dtUpdate = LocalDateTime.now().plusSeconds(1L);
        BDDMockito.given(userRepository.findById(id))
                .willReturn(Optional.of(userToUpdate));
        Mockito.when(userConverter.convertFromOptionalToEntity(any()))
                .thenReturn(userToUpdate);
        BDDMockito.given(userRepository.saveAndFlush(any(UserEntity.class)))
                .willReturn(DataUtils.getUserPersisted());
        //when
        UserEntity updatedUser = serviceUnderTest.update(userToUpdate, id, dtUpdate);
        //then
        assertThat(updatedUser).isNotNull();
        verify(userRepository, times(1)).saveAndFlush(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test update user with unacceptable dtUpdate")
    public void givenUserToUpdate_whenUpdateUser_thenExceptionIsThrown() {
        //given
        UserEntity userToUpdate = DataUtils.getUserTransient();
        UUID id = userToUpdate.getId();
        LocalDateTime dtUpdate = userToUpdate.getDtUpdate();
        BDDMockito.given(userRepository.findById(id))
                .willReturn(Optional.of(userToUpdate));
        Mockito.when(userConverter.convertFromOptionalToEntity(any()))
                .thenReturn(userToUpdate);
        BDDMockito.given(userRepository.saveAndFlush(any(UserEntity.class)))
                .willReturn(DataUtils.getUserPersisted());
        //when
        assertThrows(ValidationException.class, () -> serviceUnderTest.update(userToUpdate, id, dtUpdate));
        //then
        verify(userRepository, never()).saveAndFlush(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test findInfo user by id functionality")
    public void givenId_whenFindById_thenUserIsReturned() {
        //given
        BDDMockito.given(userRepository.findById(any(UUID.class)))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));
        Mockito.when(userConverter.convertFromOptionalToEntity(any())).thenReturn(DataUtils.getUserPersisted());
        Mockito.when(userConverter.convertFromEntityToDTO(any())).thenReturn(DataUtils.getUserDTOPersisted());
        //when
        UserDTO obtainedUser = serviceUnderTest.findById(UUID.fromString("eaf4ca36-e201-43c9-9276-3e273ec93f27"));
        //then
        assertThat(obtainedUser).isNotNull();
    }

    @Test
    @DisplayName("Test findInfo user by wrong id functionality")
    public void givenIncorrectId_whenFindById_thenExceptionIsThrown() {
        //given
        BDDMockito.given(userRepository.findById(any(UUID.class)))
                .willReturn(Optional.empty());
        //when
        assertThrows(EntityNotFoundException.class, () -> serviceUnderTest.findById(any(UUID.class)));
        //then
    }

    @Test
    @DisplayName("Test register user functionality")
    public void givenUserToRegister_whenRegisterUser_thenRepositoryIsCalled() {
        //given
        UserEntity userToRegister = DataUtils.getUserTransient();
        BDDMockito.given(userRepository.findByMail(anyString()))
                .willReturn(null);
        BDDMockito.given(userRepository.saveAndFlush(any(UserEntity.class)))
                .willReturn(DataUtils.getUserPersisted());
        //when
        UserEntity registeredUser = serviceUnderTest.register(userToRegister);
        //then
        assertThat(registeredUser).isNotNull();
        verify(verificationQueueService, times(1)).add(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test register user with duplicate email functionality")
    public void givenUserToRegisterWithDuplicateEmail_whenRegister_thenExceptionIsThrown() {
        //given
        UserEntity userToRegister = DataUtils.getUserTransient();
        BDDMockito.given(userRepository.existsByMail(anyString()))
                .willReturn(true);
        //when
        assertThrows(ValidationException.class, () -> serviceUnderTest.register(userToRegister));
        //then
        verify(userRepository, never()).saveAndFlush(any(UserEntity.class));
        verify(verificationQueueService, never()).add(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test find user info functionality")
    public void givenUserDetails_whenFindInfo_thenRepositoryIsCalled() {
        //given
        UserDetailsDTO userDetails = new UserDetailsDTO(UUID.randomUUID(), "test@mail.com", "Test", Role.USER);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + userDetails.getRole()));
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        BDDMockito.given(userRepository.findById(userDetails.getId()))
                .willReturn(Optional.of(DataUtils.getUserPersisted()));
        Mockito.when(userConverter.convertFromOptionalToEntity(any()))
                .thenReturn(DataUtils.getUserPersisted());
        Mockito.when(userConverter.convertFromEntityToDTO(any()))
                .thenReturn(DataUtils.getUserDTOPersisted());
        //when
        UserDTO info = serviceUnderTest.findInfo();
        //then
        assertThat(info).isNotNull();
    }

    @Test
    @DisplayName("Test get page of users functionality")
    public void givenPageParams_whenGetPage_thenRepositoryIsCalled() {
        //given
        List<UserEntity> entities = List.of(DataUtils.getUserPersisted(), DataUtils.getUserOnePersisted(), DataUtils.getUserTwoPersisted());
        int page = 1;
        int size = 20;
        Pageable pageRequest = PageRequest.of(page, size);
        BDDMockito.given(userRepository.findAll(pageRequest))
                .willReturn(new PageImpl<UserEntity>(entities, pageRequest, size));
        //when
        Page<UserDTO> testPage = serviceUnderTest.getPage(pageRequest);
        //then
        assertThat(testPage).isNotNull();
        assertThat(testPage.getNumberOfElements()).isEqualTo(entities.size());
        verify(userRepository, times(1)).findAll(pageRequest);

    }
}
