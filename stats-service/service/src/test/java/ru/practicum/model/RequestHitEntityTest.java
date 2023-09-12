package ru.practicum.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestHitEntityTest {

    private RequestHitEntity requestHitEntity;

    @BeforeEach
    public void setUp() {
        requestHitEntity = new RequestHitEntity();
    }

    @Test
    public void testId() {
        requestHitEntity.setId(1L);
        assertEquals(1L, requestHitEntity.getId());
    }

    @Test
    public void testUri() {
        requestHitEntity.setUri("example.com");
        assertEquals("example.com", requestHitEntity.getUri());
    }

    @Test
    public void testTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        requestHitEntity.setTimestamp(now);
        assertEquals(now, requestHitEntity.getTimestamp());
    }

    @Test
    public void testIp() {
        requestHitEntity.setIp("127.0.0.1");
        assertEquals("127.0.0.1", requestHitEntity.getIp());
    }

    @Test
    public void testApp() {
        requestHitEntity.setApp("app");
        assertEquals("app", requestHitEntity.getApp());
    }
}