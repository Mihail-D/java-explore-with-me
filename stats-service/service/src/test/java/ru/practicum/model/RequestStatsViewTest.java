package ru.practicum.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestStatsViewTest {

    private RequestStatsView requestStatsView;

    @BeforeEach
    public void setUp() {
        requestStatsView = new RequestStatsView("TestApp", "/test/uri", 100);
    }

    @Test
    public void testGetApp() {
        assertEquals("TestApp", requestStatsView.getApp());
    }

    @Test
    public void testGetUri() {
        assertEquals("/test/uri", requestStatsView.getUri());
    }

    @Test
    public void testGetCount() {
        assertEquals(100, requestStatsView.getCount());
    }

    @Test
    public void testSetApp() {
        requestStatsView.setApp("NewApp");
        assertEquals("NewApp", requestStatsView.getApp());
    }

    @Test
    public void testSetUri() {
        requestStatsView.setUri("/new/uri");
        assertEquals("/new/uri", requestStatsView.getUri());
    }

    @Test
    public void testSetCount() {
        requestStatsView.setCount(200);
        assertEquals(200, requestStatsView.getCount());
    }
}