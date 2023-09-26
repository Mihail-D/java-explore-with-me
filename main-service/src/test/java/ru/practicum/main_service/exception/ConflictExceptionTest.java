package ru.practicum.main_service.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ConflictExceptionTest {

    @Test
    public void shouldTestCreateInstanceWithMessage() {
        String message = "Test message";
        ConflictException exception = new ConflictException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestCreateMultipleInstancesWithDifferentMessages() {
        String message1 = "Test message 1";
        String message2 = "Test message 2";
        ConflictException exception1 = new ConflictException(message1);
        ConflictException exception2 = new ConflictException(message2);
        assertEquals(message1, exception1.getMessage());
        assertEquals(message2, exception2.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithNullMessage() {
        String message = null;
        ConflictException exception = new ConflictException(message);
        assertNull(exception.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithEmptyMessage() {
        String message = "";
        ConflictException exception = new ConflictException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithLongMessage() {
        String message = "This is a very long message that exceeds the maximum length allowed for a message in ConflictException";
        ConflictException exception = new ConflictException(message);
        assertEquals(message, exception.getMessage());
    }

    @Test
    public void shouldTestCreateInstanceWithSpecialCharactersInMessage() {
        String message = "This is a message with special characters: !@#$%^&*()";
        ConflictException exception = new ConflictException(message);
        assertEquals(message, exception.getMessage());
    }
}