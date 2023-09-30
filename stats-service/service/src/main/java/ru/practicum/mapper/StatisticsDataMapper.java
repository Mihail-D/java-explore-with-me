package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.EndpointHitData;
import ru.practicum.StatisticsResponseData;
import ru.practicum.model.StatisticsEntity;
import ru.practicum.model.StatisticsViewData;

@UtilityClass
public class StatisticsDataMapper {
    public StatisticsEntity toStats(EndpointHitData endpointHitRequestDto) {
        return StatisticsEntity.builder()
                .ip(endpointHitRequestDto.getIp())
                .timestamp(endpointHitRequestDto.getTimestamp())
                .uri(endpointHitRequestDto.getUri())
                .app(endpointHitRequestDto.getApp())
                .build();
    }

    public StatisticsResponseData toStatsResponseDto(StatisticsViewData viewStats) {
        return StatisticsResponseData.builder()
                .app(viewStats.getApp())
                .uri(viewStats.getUri())
                .hits(viewStats.getCount())
                .build();
    }
}
