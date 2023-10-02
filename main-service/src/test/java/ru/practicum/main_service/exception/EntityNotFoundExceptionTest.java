package ru.practicum.main_service.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class EntityNotFoundExceptionTest {

    @Test
    public void shouldTestSetMessageProperty() {
        String message = "Test message";
        EntityNotFoundException exception = new EntityNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestGetMessage() {
        String message = "Test message";
        EntityNotFoundException exception = new EntityNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestNullMessageParameter() {
        assertThrows(NullPointerException.class, () -> {
            throw new NullPointerException();
        });
    }

    @Test
    public void shouldTestEmptyMessageParameter() {
        String expectedMessage = "";
        EntityNotFoundException exception = new EntityNotFoundException("");
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldTestSpecialCharactersMessageParameter() {
        String message = "Test message with special characters: !@#$%^&*()";
        EntityNotFoundException exception = new EntityNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}