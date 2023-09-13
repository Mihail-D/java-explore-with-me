package ru.practicum.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.practicum.model.RequestStatsView;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class StatisticsQueryRepositoryTest {

    @Mock
    private StatisticsQueryRepository statisticsQueryRepository;

    @Test
    public void shouldTestGetViewStatsByDateRange() {
        LocalDateTime start = LocalDateTime.now().minusDays(1);
        LocalDateTime end = LocalDateTime.now();

        RequestStatsView statsView1 = new RequestStatsView("app1", "/test1", 10);
        RequestStatsView statsView2 = new RequestStatsView("app2", "/test2", 20);

        when(statisticsQueryRepository.getViewStatsByDateRange(start, end)).thenReturn(Arrays.asList(statsView1, statsView2));

        List<RequestStatsView> result = statisticsQueryRepository.getViewStatsByDateRange(start, end);

        assertEquals(2, result.size());
        assertEquals("app1", result.get(0).getApp());
        assertEquals("/test1", result.get(0).getUri());
        assertEquals(10, result.get(0).getCount());

        assertEquals("app2", result.get(1).getApp());
        assertEquals("/test2", result.get(1).getUri());
        assertEquals(20, result.get(1).getCount());
    }
}