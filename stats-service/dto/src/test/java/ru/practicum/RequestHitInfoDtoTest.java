package ru.practicum;

import org.junit.Test;

import javax.validation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;

import static org.junit.Assert.*;

public class RequestHitInfoDtoTest {

    @Test
    public void shouldCreateRequestHitInfoDtoWithValidParameters() {
        EndpointHitData requestHitInfoDto = EndpointHitData.builder()
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
        EndpointHitData requestHitInfoDto = EndpointHitData.builder()
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

        EndpointHitData requestHitInfoDto = EndpointHitData.builder()
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
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri(null)
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri("example.com")
                    .ip(null)
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app(null)
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    public void shouldExceptionThrownWhenCreatingRequestHitInfoDtoWithEmptyFields() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri("")
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri("example.com")
                    .ip("")
                    .app("TestApp")
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });

        assertThrows(ConstraintViolationException.class, () -> {
            EndpointHitData requestHitInfoDto = EndpointHitData.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app("")
                    .timestamp(LocalDateTime.now())
                    .build();

            Set<ConstraintViolation<EndpointHitData>> violations = validator.validate(requestHitInfoDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        });
    }


    @Test
    public void shouldExceptionThrownWhenCreatingRequestHitInfoDtoWithInvalidTimestampFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            EndpointHitData.builder()
                    .uri("example.com")
                    .ip("127.0.0.1")
                    .app("TestApp")
                    .timestamp(LocalDateTime.parse("2022-13-01 12:00:00"))
                    .build();
        });
    }
}