package ru.practicum.main_service.request.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.event.dto.EventRequestStatusUpdateResult;
import ru.practicum.main_service.request.model.Request;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RequestMapper {

    public static ParticipationRequestDto toRequestDto(Request request) {
        return ParticipationRequestDto.builder()
                .id(request.getId())
                .created(request.getCreated())
                .status(request.getStatus())
                .requester(request.getRequester() != null ? request.getRequester().getId() : null)
                .event(request.getEvent() != null ? request.getEvent().getId() : null)
                .build();
    }

    public static EventRequestStatusUpdateResult toUpdateResultDto(
            List<Request> confirmedRequests,
            List<Request> rejectedRequests
    ) {
        return EventRequestStatusUpdateResult.builder()
                .confirmedRequests(confirmedRequests != null ? confirmedRequests.stream().map(RequestMapper::toRequestDto).collect(Collectors.toList()) : null)
                .rejectedRequests(rejectedRequests != null ? rejectedRequests.stream().map(RequestMapper::toRequestDto).collect(Collectors.toList()) : null)
                .build();
    }
}