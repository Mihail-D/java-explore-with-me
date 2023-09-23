package ru.practicum.main_service.users.dto;

import org.junit.Test;

import javax.validation.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class UserDtoTest {

    @Test
    public void shouldCreateUserDtoWithValidFields() {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("John Doe")
                .email("johndoe@example.com")
                .build();

        assertEquals(Long.valueOf(1L), userDto.getId());
        assertEquals("John Doe", userDto.getName());
        assertEquals("johndoe@example.com", userDto.getEmail());
    }

    @Test
    public void shouldSetAndGetIdField() {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        assertEquals(Long.valueOf(1L), userDto.getId());
    }

    @Test
    public void shouldCreateUserDtoWithEmptyName() {
        assertThrows(ConstraintViolationException.class, () -> {
            UserDto userDto = UserDto.builder()
                    .id(1L)
                    .name("")
                    .email("johndoe@example.com")
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldCreateUserDtoWithEmptyEmail() {
        assertThrows(ConstraintViolationException.class, () -> {
            UserDto userDto = UserDto.builder()
                    .id(1L)
                    .name("John Doe")
                    .email("")
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldCreateUserDtoWithInvalidEmailFormat() {
        assertThrows(ConstraintViolationException.class, () -> {
            UserDto userDto = UserDto.builder()
                    .id(1L)
                    .name("John Doe")
                    .email("invalid_email")
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<UserDto>> violations = validator.validate(userDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }
}