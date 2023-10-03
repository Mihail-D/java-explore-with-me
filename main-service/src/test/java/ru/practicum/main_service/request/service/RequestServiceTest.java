package ru.practicum.main_service.request.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import ru.practicum.main_service.event.model.Event;
import ru.practicum.main_service.exception.EntityNotFoundException;
import ru.practicum.main_service.request.RequestRepository;
import ru.practicum.main_service.request.dto.ParticipationRequestDto;
import ru.practicum.main_service.request.model.ParticipationRequestStatus;
import ru.practicum.main_service.request.model.Request;
import ru.practicum.main_service.users.model.User;
import ru.practicum.main_service.users.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest {

    @Mock
    private RequestRepository requestsRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RequestServiceImpl requestService;


    @Test
    public void shouldTestReturnsEmptyListIfNoRequestsFound() {
        Long userId = 1L;
        List<Request> requestsList = new ArrayList<>();

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        Mockito.when(requestsRepository.findByRequesterId(userId)).thenReturn(requestsList);

        List<ParticipationRequestDto> result = requestService.getRequest(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldTestThrowsObjectNotFoundExceptionIfUserIdDoesNotExist() {
        Long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> requestService.getRequest(userId));
    }

    @Test
    public void shouldTestReturnsListWithCorrectDataForGivenUserId() {
        Long userId = 1L;
        List<Request> requestsList = new ArrayList<>();
        Request request1 = new Request();
        request1.setId(1L);
        request1.setCreated(LocalDateTime.now());
        request1.setStatus(ParticipationRequestStatus.CONFIRMED);
        User user = new User();
        user.setId(userId);
        request1.setRequester(user);
        Event event = new Event();
        event.setId(2L);
        request1.setEvent(event);
        requestsList.add(request1);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(requestsRepository.findByRequesterId(userId)).thenReturn(requestsList);

        List<ParticipationRequestDto> result = requestService.getRequest(userId);

        assertEquals(1, result.size());
        ParticipationRequestDto dto = result.get(0);
        assertEquals(request1.getId(), dto.getId());
        assertEquals(request1.getCreated(), dto.getCreated());
        assertEquals(request1.getStatus(), dto.getStatus());
        assertEquals(request1.getRequester().getId(), dto.getRequester());
        assertEquals(request1.getEvent().getId(), dto.getEvent());
    }

    @Test
    public void shouldTestReturnsListWithCorrectDataForUserIdWithMultipleRequests() {
        Long userId = 1L;
        List<Request> requestsList = new ArrayList<>();
        User user = new User();
        user.setId(userId);
        Event event1 = new Event();
        event1.setId(2L);
        Event event2 = new Event();
        event2.setId(3L);

        Request request1 = new Request();
        request1.setId(1L);
        request1.setCreated(LocalDateTime.now());
        request1.setStatus(ParticipationRequestStatus.CONFIRMED);
        request1.setRequester(user);
        request1.setEvent(event1);

        Request request2 = new Request();
        request2.setId(2L);
        request2.setCreated(LocalDateTime.now());
        request2.setStatus(ParticipationRequestStatus.PENDING);
        request2.setRequester(user);
        request2.setEvent(event2);

        requestsList.add(request1);
        requestsList.add(request2);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(requestsRepository.findByRequesterId(userId)).thenReturn(requestsList);

        List<ParticipationRequestDto> result = requestService.getRequest(userId);

        assertEquals(2, result.size());

        ParticipationRequestDto dto1 = result.get(0);
        assertEquals(request1.getId(), dto1.getId());
        assertEquals(request1.getCreated(), dto1.getCreated());
        assertEquals(request1.getStatus(), dto1.getStatus());
        assertEquals(request1.getRequester().getId(), dto1.getRequester());
        assertEquals(request1.getEvent().getId(), dto1.getEvent());

        ParticipationRequestDto dto2 = result.get(1);
        assertEquals(request2.getId(), dto2.getId());
        assertEquals(request2.getCreated(), dto2.getCreated());
        assertEquals(request2.getStatus(), dto2.getStatus());
        assertEquals(request2.getRequester().getId(), dto2.getRequester());
        assertEquals(request2.getEvent().getId(), dto2.getEvent());
    }

}