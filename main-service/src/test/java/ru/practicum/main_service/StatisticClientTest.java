package ru.practicum.main_service;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.client.StatsServiceClient;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.dto.EventShortDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;

public class StatisticClientTest {

    @Test
    public void shouldTestSavingHitWithValidUriAndIp() {
        StatsServiceClient statsServiceClient = Mockito.mock(StatsServiceClient.class);

        StatisticClient statisticClient = new StatisticClient(statsServiceClient);

        ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);

        Mockito.when(statsServiceClient.postEndpointHit(any(RequestHitInfoDto.class))).thenReturn(responseEntity);

        ResponseEntity<Object> result = statisticClient.saveHit("/example", "127.0.0.1");

        assertEquals(responseEntity, result);
    }

    @Test
    public void shouldTestSettingViewsNumberForListOfEventsWithValidIds() {
        StatsServiceClient statsServiceClient = Mockito.mock(StatsServiceClient.class);

        StatisticClient statisticClient = new StatisticClient(statsServiceClient);

        List<EventShortDto> events = new ArrayList<>();
        events.add(EventShortDto.builder()
                .id(1L)
                .build());
        events.add(EventShortDto.builder()
                .id(2L)
                .build());

        List<StatsResponseDto> hits = new ArrayList<>();
        hits.add(StatsResponseDto.builder()
                .app("main-service")
                .uri("/events/1")
                .hits(10)
                .build());
        hits.add(StatsResponseDto.builder()
                .app("main-service")
                .uri("/events/2")
                .hits(5)
                .build());

        Mockito.when(statsServiceClient.getStatistic(any(LocalDateTime.class), any(LocalDateTime.class), anyList(), anyBoolean())).thenReturn(hits);

        List<EventShortDto> result = statisticClient.setViewsNumber(events);

        assertEquals(10, (int) result.get(0).getViews());
        assertEquals(5, (int) result.get(1).getViews());
    }

    @Test
    public void shouldTestSavingHitWithEmptyUri() {
        StatsServiceClient statsServiceClient = Mockito.mock(StatsServiceClient.class);

        StatisticClient statisticClient = new StatisticClient(statsServiceClient);

        RequestHitInfoDto hitRequestDto = RequestHitInfoDto.builder()
                .app("main-service")
                .uri("")
                .ip("127.0.0.1")
                .timestamp(LocalDateTime.now())
                .build();

        ResponseEntity<Object> result = statisticClient.saveHit("", "127.0.0.1");

        assertNull(result);
    }

    @Test
    public void shouldTestSavingHitWithEmptyIp() {
        StatsServiceClient statsServiceClient = Mockito.mock(StatsServiceClient.class);

        StatisticClient statisticClient = new StatisticClient(statsServiceClient);

        RequestHitInfoDto hitRequestDto = RequestHitInfoDto.builder()
                .app("main-service")
                .uri("/example")
                .ip("")
                .timestamp(LocalDateTime.now())
                .build();

        ResponseEntity<Object> result = statisticClient.saveHit("/example", "");

        assertNull(result);
    }

    @Test
    public void shouldTestSettingViewsNumberForEventWithInvalidId() {
        StatsServiceClient statsServiceClient = Mockito.mock(StatsServiceClient.class);

        StatisticClient statisticClient = new StatisticClient(statsServiceClient);

        EventFullDto event = EventFullDto.builder()
                .id(-1L)
                .createdOn(LocalDateTime.now())
                .build();

        EventFullDto result = statisticClient.setViewsNumber(event);

        assertEquals(0L, (long) result.getViews());
    }
}