package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.EndpointHitData;
import ru.practicum.StatisticsResponseData;
import ru.practicum.exception.ValidationException;
import ru.practicum.mapper.StatisticsDataMapper;
import ru.practicum.model.StatisticsViewData;
import ru.practicum.repository.StatisticsDataRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticsDataServiceImpl implements StatisticsDataService {
    private final StatisticsDataRepository statisticRepository;

    @Override
    public void postHit(EndpointHitData hitRequestDto) {
        statisticRepository.save(StatisticsDataMapper.toStats(hitRequestDto));
    }

    @Override
    public List<StatisticsResponseData> getStatistics(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {
        List<StatisticsViewData> viewStatsList;

        if (start.isAfter(end)) {
            throw new ValidationException("Время начала не может быть позднее даты конца диапазона!");
        }
        if (uris == null || uris.isEmpty()) {
            if (unique) {
                viewStatsList = statisticRepository.findAllStatsByUniqIp(start, end);
            } else {
                viewStatsList = statisticRepository.findAllByDateBetween(start, end);
            }
        } else {
            if (unique) {
                viewStatsList = statisticRepository.findStatsByUrisByUniqIp(start, end, uris);
            } else {
                viewStatsList = statisticRepository.findAllByDateBetween(start, end, uris);
            }

        }

        return viewStatsList.stream()
                .map(StatisticsDataMapper::toStatsResponseDto).collect(Collectors.toList());

    }
}
