package ru.practicum.mapper;

import org.junit.Test;
import ru.practicum.StatsResponseDto;
import ru.practicum.model.RequestStatsView;

import static org.junit.Assert.assertEquals;

public class StatsDtoModelMapperTest {

    @Test
    public void test_valid_values() {
        RequestStatsView viewStats = new RequestStatsView("app", "uri", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("uri")
                .hits(10)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMinimumValues() {
        RequestStatsView viewStats = new RequestStatsView("", "", 0);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("")
                .uri("")
                .hits(0)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMaximumValues() {
        RequestStatsView viewStats = new RequestStatsView("app", "uri", Long.MAX_VALUE);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("uri")
                .hits(Long.MAX_VALUE)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestNullValues() {
        RequestStatsView viewStats = new RequestStatsView(null, null, 0);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app(null)
                .uri(null)
                .hits(0)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyApp() {
        RequestStatsView viewStats = new RequestStatsView("", "uri", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("")
                .uri("uri")
                .hits(10)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyUri() {
        RequestStatsView viewStats = new RequestStatsView("app", "", 10);
        StatsResponseDto expected = StatsResponseDto.builder()
                .app("app")
                .uri("")
                .hits(10)
                .build();

        StatsResponseDto result = StatsDtoModelMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }
}