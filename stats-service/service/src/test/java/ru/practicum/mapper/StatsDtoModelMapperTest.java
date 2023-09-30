package ru.practicum.mapper;

import org.junit.Test;
import ru.practicum.StatisticsResponseData;
import ru.practicum.model.StatisticsViewData;

import static org.junit.Assert.assertEquals;

public class StatsDtoModelMapperTest {

    @Test
    public void test_valid_values() {
        StatisticsViewData viewStats = new StatisticsViewData("app", "uri", 10);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app("app")
                .uri("uri")
                .hits(10)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMinimumValues() {
        StatisticsViewData viewStats = new StatisticsViewData("", "", 0);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app("")
                .uri("")
                .hits(0)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestMaximumValues() {
        StatisticsViewData viewStats = new StatisticsViewData("app", "uri", Long.MAX_VALUE);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app("app")
                .uri("uri")
                .hits(Long.MAX_VALUE)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestNullValues() {
        StatisticsViewData viewStats = new StatisticsViewData(null, null, 0);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app(null)
                .uri(null)
                .hits(0)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyApp() {
        StatisticsViewData viewStats = new StatisticsViewData("", "uri", 10);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app("")
                .uri("uri")
                .hits(10)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }

    @Test
    public void shouldTestEmptyUri() {
        StatisticsViewData viewStats = new StatisticsViewData("app", "", 10);
        StatisticsResponseData expected = StatisticsResponseData.builder()
                .app("app")
                .uri("")
                .hits(10)
                .build();

        StatisticsResponseData result = StatisticsDataMapper.toStatsResponseDto(viewStats);

        assertEquals(expected, result);
    }
}