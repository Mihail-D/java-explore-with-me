package ru.practicum.main_service.users.dto;

import org.junit.Test;
import ru.practicum.main_service.users.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserMapperTest {

    @Test
    public void shouldMapUserObjectToUserDtoObjectCorrectly() {
        User user = User.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .build();

        UserDto userDto = UserMapper.toUserDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldMapNewUserRequestDtoObjectToUserObjectCorrectly() {
        NewUserRequestDto userRequestDto = NewUserRequestDto.builder()
                .name("John")
                .email("john@example.com")
                .build();

        User user = UserMapper.toUser(userRequestDto);

        assertEquals(userRequestDto.getName(), user.getName());
        assertEquals(userRequestDto.getEmail(), user.getEmail());
    }

    @Test
    public void shouldMapUserObjectWithNullIdToUserDtoObjectCorrectly() {
        User user = User.builder()
                .name("John")
                .email("john@example.com")
                .build();

        UserDto userDto = UserMapper.toUserDto(user);

        assertNull(userDto.getId());
        assertEquals(user.getName(), userDto.getName());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void shouldMapNewUserRequestDtoObjectWithNullNameToUserObjectCorrectly() {
        NewUserRequestDto userRequestDto = NewUserRequestDto.builder()
                .email("john@example.com")
                .build();

        User user = UserMapper.toUser(userRequestDto);

        assertNull(user.getName());
        assertEquals(userRequestDto.getEmail(), user.getEmail());
    }

}