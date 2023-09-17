package ru.practicum.mapper;

import lombok.experimental.UtilityClass;
import ru.practicum.RequestHitInfoDto;
import ru.practicum.StatsResponseDto;
import ru.practicum.model.RequestHitEntity;
import ru.practicum.model.RequestStatsView;

@UtilityClass
public class StatsDtoModelMapper {

    public static StatsResponseDto toStatsResponseDto(RequestStatsView viewStats) {
        return StatsResponseDto.builder()
                .app(viewStats.getApp())
                .uri(viewStats.getUri())
                .hits(viewStats.getCount())
                .build();
    }

    public static RequestHitEntity toStats(RequestHitInfoDto endpointHitRequestDto) {
        return RequestHitEntity.builder()
                .ip(endpointHitRequestDto.getIp())
                .timestamp(endpointHitRequestDto.getTimestamp())
                .uri(endpointHitRequestDto.getUri())
                .app(endpointHitRequestDto.getApp())
                .build();
    }
}