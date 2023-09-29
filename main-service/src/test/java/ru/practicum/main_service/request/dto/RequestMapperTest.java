package ru.practicum.main_service.request.dto;

import org.junit.Test;
import ru.practicum.main_service.event.dto.EventRequestStatusUpdateResult;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.request.model.ParticipationRequestStatus;
import ru.practicum.main_service.request.model.Request;
import ru.practicum.main_service.users.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestMapperTest {

    @Test
    public void shouldTestMappingRequestToDto() {
        Request request = new Request();
        request.setId(1L);
        request.setCreated(LocalDateTime.now());
        request.setStatus(ParticipationRequestStatus.CONFIRMED);
        User requester = new User();
        requester.setId(1L);
        request.setRequester(requester);
        Event event = new Event();
        event.setId(1L);
        request.setEvent(event);

        ParticipationRequestDto dto = RequestMapper.toRequestDto(request);

        assertEquals(request.getId(), dto.getId());
        assertEquals(request.getCreated(), dto.getCreated());
        assertEquals(request.getStatus(), dto.getStatus());
        assertEquals(request.getRequester().getId(), dto.getRequester());
        assertEquals(request.getEvent().getId(), dto.getEvent());
    }

    @Test
    public void shouldTMappingRequestsToUpdateResult() {
        Request confirmedRequest1 = new Request();
        confirmedRequest1.setId(1L);
        confirmedRequest1.setCreated(LocalDateTime.now());
        confirmedRequest1.setStatus(ParticipationRequestStatus.CONFIRMED);
        User requester1 = new User();
        requester1.setId(1L);
        confirmedRequest1.setRequester(requester1);
        Event event1 = new Event();
        event1.setId(1L);
        confirmedRequest1.setEvent(event1);

        Request confirmedRequest2 = new Request();
        confirmedRequest2.setId(2L);
        confirmedRequest2.setCreated(LocalDateTime.now());
        confirmedRequest2.setStatus(ParticipationRequestStatus.CONFIRMED);
        User requester2 = new User();
        requester2.setId(2L);
        confirmedRequest2.setRequester(requester2);
        Event event2 = new Event();
        event2.setId(2L);
        confirmedRequest2.setEvent(event2);

        List<Request> confirmedRequests = Arrays.asList(confirmedRequest1, confirmedRequest2);

        Request rejectedRequest1 = new Request();
        rejectedRequest1.setId(3L);
        rejectedRequest1.setCreated(LocalDateTime.now());
        rejectedRequest1.setStatus(ParticipationRequestStatus.REJECTED);
        User requester3 = new User();
        requester3.setId(3L);
        rejectedRequest1.setRequester(requester3);
        Event event3 = new Event();
        event3.setId(3L);
        rejectedRequest1.setEvent(event3);

        Request rejectedRequest2 = new Request();
        rejectedRequest2.setId(4L);
        rejectedRequest2.setCreated(LocalDateTime.now());
        rejectedRequest2.setStatus(ParticipationRequestStatus.REJECTED);
        User requester4 = new User();
        requester4.setId(4L);
        rejectedRequest2.setRequester(requester4);
        Event event4 = new Event();
        event4.setId(4L);
        rejectedRequest2.setEvent(event4);

        List<Request> rejectedRequests = Arrays.asList(rejectedRequest1, rejectedRequest2);

        EventRequestStatusUpdateResult result = RequestMapper.toUpdateResultDto(confirmedRequests, rejectedRequests);

        assertEquals(confirmedRequests.size(), result.getConfirmedRequests().size());
        assertEquals(rejectedRequests.size(), result.getRejectedRequests().size());
    }

    @Test
    public void shouldTestMappingEmptyRequestsToUpdateResult() {
        List<Request> confirmedRequests = new ArrayList<>();
        List<Request> rejectedRequests = new ArrayList<>();

        EventRequestStatusUpdateResult result = RequestMapper.toUpdateResultDto(confirmedRequests, rejectedRequests);

        assertTrue(result.getConfirmedRequests().isEmpty());
        assertTrue(result.getRejectedRequests().isEmpty());
    }
}