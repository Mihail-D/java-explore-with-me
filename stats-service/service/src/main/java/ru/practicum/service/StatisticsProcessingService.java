package ru.practicum.service;

import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticsProcessingService {
    void postHit(RequestHitInfoDto hitRequestDto);

    List<StatsResponseDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique);
}
