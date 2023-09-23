package ru.practicum.main_service.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ValidationExceptionTest {

    @Test
    public void shouldTestCreateInstanceWithMessageParameter() {
        String message = "Test message";
        ValidationException exception = new ValidationException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestThrowExceptionWithMessageParameter() {
        String message = "Test message";
        try {
            throw new ValidationException(message);
        } catch (ValidationException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    @Test
    public void shouldTestCreateInstanceWithNullMessageParameter() {
        ValidationException exception = new ValidationException(null);
        assertNull(exception.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithEmptyMessageParameter() {
        ValidationException exception = new ValidationException("");
        assertEquals("", exception.getMessage());
    }


    @Test
    public void shouldTestCreateInstanceWithSpecialCharactersInMessageParameter() {
        String message = "Test message with special characters: !@#$%^&*";
        ValidationException exception = new ValidationException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithNonASCIICharactersInMessageParameter() {
        String message = "Test message with non-ASCII characters: äöü";
        ValidationException exception = new ValidationException(message);
        assertEquals(message, exception.getMessage());
    }
}