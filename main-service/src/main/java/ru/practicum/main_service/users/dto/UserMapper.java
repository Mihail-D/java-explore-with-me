package ru.practicum.main_service.users.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.users.model.User;

@UtilityClass
public class UserMapper {

    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public static User toUser(NewUserRequestDto userRequestDto) {
        if (userRequestDto == null) {
            return null;
        }
        return User.builder()
                .email(userRequestDto.getEmail())
                .name(userRequestDto.getName())
                .build();
    }
}