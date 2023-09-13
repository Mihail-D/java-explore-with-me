package ru.practicum;

import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class RequestHitInfoDtoTest {

    @Test
    public void shouldCreateRequestHitInfoDtoWithValidParameters() {
        RequestHitInfoDto requestHitInfoDto = RequestHitInfoDto.builder()
                .uri("example.com")
                .ip("127.0.0.1")
                .app("TestApp")
                .timestamp(LocalDateTime.now())
                .build();

        assertNotNull(requestHitInfoDto);
        assertEquals("example.com", requestHitInfoDto.getUri());
        assertEquals("127.0.0.1", requestHitInfoDto.getIp());
        assertEquals("TestApp", requestHitInfoDto.getApp());
        assertNotNull(requestHitInfoDto.getTimestamp());
    }

    @Test
    public void shouldAccessAndModifyUriIpAndAppFields() {
        RequestHitInfoDto requestHitInfoDto = RequestHitInfoDto.builder()
                .uri("example.com")
                .ip("127.0.0.1")
                .app("TestApp")
                .timestamp(LocalDateTime.now())
                .build();

        assertEquals("example.com", requestHitInfoDto.getUri());
        assertEquals("127.0.0.1", requestHitInfoDto.getIp());
        assertEquals("TestApp", requestHitInfoDto.getApp());

        requestHitInfoDto.setUri("newexample.com");
        requestHitInfoDto.setIp("192.168.0.1");
        requestHitInfoDto.setApp("NewTestApp");

        assertEquals("newexample.com", requestHitInfoDto.getUri());
        assertEquals("192.168.0.1", requestHitInfoDto.getIp());
        assertEquals("NewTestApp", requestHitInfoDto.getApp());
    }

    @Test
    public void shouldAccessAndModifyTimestampField() {
        LocalDateTime timestamp = LocalDateTime.now();

        RequestHitInfoDto requestHitInfoDto = RequestHitInfoDto.builder()
                .uri("example.com")
                .ip("127.0.0.1")
                .app("TestApp")
                .timestamp(timestamp)
                .build();

        assertEquals(timestamp, requestHitInfoDto.getTimestamp());

        LocalDateTime newTimestamp = LocalDateTime.now();
        requestHitInfoDto.setTimestamp(newTimestamp);

        assertEquals(newTimestamp, requestHitInfoDto.getTimestamp());
    }

    @Test
    public void shouldExceptionThrownWhenCreatingRequestHitInfoDtoWithNullFields() {
        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri(null)
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();
        });

        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("example.com")
                    .ip(null)
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();
        });

        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app(null)
                    .timestamp(LocalDateTime.now())
                    .build();
        });
    }

    @Test
    public void shouldExceptionThrownWhenCreatingRequestHitInfoDtoWithEmptyFields() {
        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("")
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();
        });

        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("example.com")
                    .ip("")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();
        });

        assertThrows(ConstraintViolationException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app("")
                    .timestamp(LocalDateTime.now())
                    .build();
        });
    }

    @Test
    public void shouldExceptionThrownWhenCreatingRequestHitInfoDtoWithInvalidTimestampFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            RequestHitInfoDto.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.parse("2022-13-01 12:00:00"))
                    .build();
        });
    }
}