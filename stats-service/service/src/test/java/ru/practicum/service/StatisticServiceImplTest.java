package ru.practicum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.model.RequestHitEntity;
import ru.practicum.model.RequestStatsView;
import ru.practicum.repository.StatisticsQueryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticServiceImplTest {

    @Mock
    private StatisticsQueryRepository statisticRepository;
    @InjectMocks
    StatisticsProcessingServiceImpl statisticService;
    private RequestHitInfoDto hitRequestDto;

    @BeforeEach
    void beforeEach() {

        hitRequestDto = RequestHitInfoDto.builder()
                .app("app")
                .ip("ip")
                .uri("uri")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Test
    void shouldPostHitWhenIsOk() {
        statisticService.postHit(hitRequestDto);
        verify(statisticRepository).save(any(RequestHitEntity.class));

    }

    @Test
    void shouldGetStatistics() {
        LocalDateTime start = LocalDateTime.of(2023, 8, 4, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 8, 5, 0, 0, 0);
        List<String> uris = new ArrayList<>();
        Boolean unique = true;

        List<RequestStatsView> viewStatsList = new ArrayList<>();
        viewStatsList.add(new RequestStatsView("app1", "uri1", 1));
        viewStatsList.add(new RequestStatsView("app2", "uri2", 2));

        when(statisticRepository.findAllStatsByUniqIp(start, end)).thenReturn(viewStatsList);

        List<StatsResponseDto> result = statisticService.getStatistics(LocalDateTime.of(2023, 8, 4, 0, 0, 0), LocalDateTime.of(2023, 8, 5, 0, 0, 0), uris, unique);

        assertEquals(2, result.size());
        assertEquals("uri1", result.get(0).getUri());
        assertEquals("uri2", result.get(1).getUri());
    }
}