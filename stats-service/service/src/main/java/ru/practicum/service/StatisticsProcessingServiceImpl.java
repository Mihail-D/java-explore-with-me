package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.exception.ValidationException;
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
                .map(StatsDtoModelMapper::toStatsResponseDto).collect(Collectors.toList());

    }
}