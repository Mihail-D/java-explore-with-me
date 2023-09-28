package ru.practicum.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestStatsViewTest {

    private ViewStats requestStatsView;

    @BeforeEach
    public void setUp() {
        requestStatsView = new ViewStats("TestApp", "/test/uri", 100);
    }

    @Test
    public void shouldTestGetApp() {
        assertEquals("TestApp", requestStatsView.getApp());
    }

    @Test
    public void shouldTestGetUri() {
        assertEquals("/test/uri", requestStatsView.getUri());
    }

    @Test
    public void shouldTestGetCount() {
        assertEquals(100, requestStatsView.getCount());
    }

    @Test
    public void shouldTestSetApp() {
        requestStatsView.setApp("NewApp");
        assertEquals("NewApp", requestStatsView.getApp());
    }

    @Test
    public void shouldTestSetUri() {
        requestStatsView.setUri("/new/uri");
        assertEquals("/new/uri", requestStatsView.getUri());
    }

    @Test
    public void shouldTestSetCount() {
        requestStatsView.setCount(200);
        assertEquals(200, requestStatsView.getCount());
    }
}