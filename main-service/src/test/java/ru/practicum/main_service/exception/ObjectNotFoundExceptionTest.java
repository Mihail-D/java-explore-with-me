package ru.practicum.main_service.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ObjectNotFoundExceptionTest {

    @Test
    public void shouldTestSetMessageProperty() {
        String message = "Test message";
        ObjectNotFoundException exception = new ObjectNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestGetMessage() {
        String message = "Test message";
        ObjectNotFoundException exception = new ObjectNotFoundException(message);
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
        ObjectNotFoundException exception = new ObjectNotFoundException("");
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldTestSpecialCharactersMessageParameter() {
        String message = "Test message with special characters: !@#$%^&*()";
        ObjectNotFoundException exception = new ObjectNotFoundException(message);
        assertEquals(message, exception.getMessage());
    }
}