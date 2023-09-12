package ru.practicum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatsResponseDtoTest {

    @Test
    public void testStatsResponseDto() {
        // Arrange
        long expectedHits = 100L;
        String expectedApp = "TestApp";
        String expectedUri = "/test/uri";

        // Act
        StatsResponseDto statsResponseDto = StatsResponseDto.builder()
                .hits(expectedHits)
                .app(expectedApp)
                .uri(expectedUri)
                .build();

        // Assert
        assertEquals(expectedHits, statsResponseDto.getHits());
        assertEquals(expectedApp, statsResponseDto.getApp());
        assertEquals(expectedUri, statsResponseDto.getUri());
    }
}