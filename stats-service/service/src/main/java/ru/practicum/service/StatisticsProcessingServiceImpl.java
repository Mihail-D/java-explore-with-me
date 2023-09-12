package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.mapper.StatsDtoModelMapper;
import ru.practicum.model.RequestStatsView;
import ru.practicum.repository.StatisticsQueryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsProcessingServiceImpl implements StatisticsProcessingService {
    private final StatisticsQueryRepository statisticRepository;

    @Override
    public void postHit(RequestHitInfoDto hitRequestDto) {
        statisticRepository.save(StatsDtoModelMapper.toStats(hitRequestDto));
    }

    @Override
    public List<StatsResponseDto> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        List<RequestStatsView> viewStatsList;

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("The start time cannot be later than the end date of the range!");
        }
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                viewStatsList = statisticRepository.getViewStatsByDateRangeWithUniqueIP(start, end);
            } else {
                viewStatsList = statisticRepository.getViewStatsByDateRange(start, end);
            }
        } else {
            if (unique) {
                viewStatsList = statisticRepository.getViewStatsByDateRangeWithUniqueIP(start, end, uris);
            } else {
                viewStatsList = statisticRepository.getViewStatsByDateRange(start, end, uris);
            }

        }

        return viewStatsList.stream()
                .map(StatsDtoModelMapper::toStatsResponseDto).collect(Collectors.toList());

    }
}
