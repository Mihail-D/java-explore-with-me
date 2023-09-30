package ru.practicum.service;

import ru.practicum.EndpointHitData;
import ru.practicum.StatisticsResponseData;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsDataService {
    void postHit(EndpointHitData hitRequestDto);

    List<StatisticsResponseData> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
