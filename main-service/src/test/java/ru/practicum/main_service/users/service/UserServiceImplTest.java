package ru.practicum.main_service.users.service;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.users.dto.NewUserRequestDto;
import ru.practicum.main_service.users.dto.UserDto;
import ru.practicum.main_service.users.model.User;
import ru.practicum.main_service.users.repository.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Test
    public void shouldTestGetUsersValidInputReturnsListOfUserDto() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        Integer from = 0;
        Integer size = 10;
        List<User> users = Arrays.asList(
                User.builder().id(1L).name("User1").email("user1@example.com").build(),
                User.builder().id(2L).name("User2").email("user2@example.com").build(),
                User.builder().id(3L).name("User3").email("user3@example.com").build()
        );

        when(userRepository.findByIdIn(ids, PageRequest.of(from, size))).thenReturn(users);

        List<UserDto> result = userService.getUsers(ids, from, size);

        assertEquals(3, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("User2", result.get(1).getName());
        assertEquals("user2@example.com", result.get(1).getEmail());
        assertEquals("User3", result.get(2).getName());
        assertEquals("user3@example.com", result.get(2).getEmail());

        Mockito.verify(userRepository).findByIdIn(ids, PageRequest.of(from, size));
    }

    @Test
    public void shouldTestCreateUserUniqueNameAndEmailSavesUserAndReturnsUserDto() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        NewUserRequestDto userRequestDto = NewUserRequestDto.builder()
                .name("User1")
                .email("user1@example.com")
                .build();
        User user = User.builder()
                .id(1L)
                .name("User1")
                .email("user1@example.com")
                .build();

        when(userRepository.existsUserByName(userRequestDto.getName())).thenReturn(false);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        UserDto result = userService.createUser(userRequestDto);

        assertEquals("User1", result.getName());
        assertEquals("user1@example.com", result.getEmail());

        Mockito.verify(userRepository).existsUserByName(userRequestDto.getName());
        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    public void shouldTestDeleteUserExistingUserIdDeletesUserFromRepository() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        userService.deleteUser(userId);

        Mockito.verify(userRepository).deleteById(userId);
    }

    @Test
    public void shouldTestGetUsersEmptyIdsListReturnsAllUsers() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        List<Long> ids = Collections.emptyList();
        Integer from = 0;
        Integer size = 10;
        List<User> users = Arrays.asList(
                User.builder().id(1L).name("User1").email("user1@example.com").build(),
                User.builder().id(2L).name("User2").email("user2@example.com").build(),
                User.builder().id(3L).name("User3").email("user3@example.com").build()
        );

        when(userRepository.findAll(PageRequest.of(from, size))).thenReturn(new PageImpl<>(users));

        List<UserDto> result = userService.getUsers(ids, from, size);

        assertEquals(3, result.size());
        assertEquals("User1", result.get(0).getName());
        assertEquals("user1@example.com", result.get(0).getEmail());
        assertEquals("User2", result.get(1).getName());
        assertEquals("user2@example.com", result.get(1).getEmail());
        assertEquals("User3", result.get(2).getName());
        assertEquals("user3@example.com", result.get(2).getEmail());

        Mockito.verify(userRepository).findAll(PageRequest.of(from, size));
    }

    @Test
    public void shouldTestGetUsersInvalidFromAndSizeReturnsEmptyList() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        Integer from = -1;
        Integer size = 0;

        List<UserDto> result = userService.getUsers(ids, from, size);

        assertTrue(result.isEmpty());

        Mockito.verifyNoInteractions(userRepository);
    }

    @Test
    public void shouldTestCreateUserNonUniqueNameThrowsConflictException() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        UserServiceImpl userService = new UserServiceImpl(userRepository);

        NewUserRequestDto userRequestDto = NewUserRequestDto.builder()
                .name("User1")
                .email("user1@example.com")
                .build();

        when(userRepository.existsUserByName(userRequestDto.getName())).thenReturn(true);

        assertThrows(ConflictException.class, () -> userService.createUser(userRequestDto));

        Mockito.verify(userRepository).existsUserByName(userRequestDto.getName());
    }

}