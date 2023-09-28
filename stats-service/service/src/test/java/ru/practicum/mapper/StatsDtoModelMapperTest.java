package ru.practicum.mapper;

import org.junit.Test;
import ru.practicum.StatsResponseDto;
import ru.practicum.model.ViewStats;

import static org.junit.Assert.assertEquals;

public class StatsDtoModelMapperTest {

    @Test
    public void test_valid_values() {
        ViewStats viewStats = new ViewStats("app", "uri", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("uri")
                .hits(10)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMinimumValues() {
        ViewStats viewStats = new ViewStats("", "", 0);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("")
                .uri("")
                .hits(0)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMaximumValues() {
        ViewStats viewStats = new ViewStats("app", "uri", Long.MAX_VALUE);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("uri")
                .hits(Long.MAX_VALUE)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestNullValues() {
        ViewStats viewStats = new ViewStats(null, null, 0);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app(null)
                .uri(null)
                .hits(0)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyApp() {
        ViewStats viewStats = new ViewStats("", "uri", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("")
                .uri("uri")
                .hits(10)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyUri() {
        ViewStats viewStats = new ViewStats("app", "", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("")
                .hits(10)
                .build();

        StatsResponseDto result = StatsMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }
}