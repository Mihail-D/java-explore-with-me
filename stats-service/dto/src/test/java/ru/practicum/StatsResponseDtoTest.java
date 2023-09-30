package ru.practicum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsResponseDtoTest {

    @Test
    public void shouldGetStatsResponseDto() {
        long expectedHits = 100L;
        String expectedApp = "TestApp";
        String expectedUri = "/test/uri";

        StatisticsResponseData statsResponseDto = StatisticsResponseData.builder()
                .hits(expectedHits)
                .app(expectedApp)
                .uri(expectedUri)
                .build();

        assertEquals(expectedHits, statsResponseDto.getHits());
        assertEquals(expectedApp, statsResponseDto.getApp());
        assertEquals(expectedUri, statsResponseDto.getUri());
    }
}