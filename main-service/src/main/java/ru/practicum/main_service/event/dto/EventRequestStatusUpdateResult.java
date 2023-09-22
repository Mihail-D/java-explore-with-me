package ru.practicum.main_service.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import ru.practicum.main_service.request.dto.ParticipationRequestDto;

import java.util.List;

@Data
@Value
@Builder
@AllArgsConstructor
public class EventRequestStatusUpdateResult {

    List<ParticipationRequestDto> confirmedRequests;
    List<ParticipationRequestDto> rejectedRequests;
}