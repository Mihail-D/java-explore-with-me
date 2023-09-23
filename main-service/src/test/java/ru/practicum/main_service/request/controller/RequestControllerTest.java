package ru.practicum.main_service.request.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.practicum.main_service.request.dto.ParticipationRequestDto;
import ru.practicum.main_service.request.service.RequestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RequestControllerTest {

    private RequestController requestController;
    private RequestService requestService;

    @BeforeEach
    public void setUp() {
        requestService = Mockito.mock(RequestService.class);
        requestController = new RequestController(requestService);
    }

    @Test
    public void shouldTestGetRequestValidUserId() {
        Long userId = 1L;

        List<ParticipationRequestDto> result = requestController.getRequest(userId);

        assertNotNull(result);
    }

    @Test
    public void shouldTestCreateRequestValidUserIdAndEventId() {
        Long userId = 1L;
        Long eventId = 1L;

        ParticipationRequestDto mockDto = new ParticipationRequestDto();

        Mockito.when(requestService.createRequest(userId, eventId)).thenReturn(mockDto);

        ParticipationRequestDto result = requestController.createRequest(userId, eventId);

        assertNotNull(result);
    }

    @Test
    public void shouldTestCancelRequestValidUserIdAndRequestId() {
        Long userId = 1L;
        Long requestId = 1L;

        ParticipationRequestDto mockDto = new ParticipationRequestDto();

        Mockito.when(requestService.cancelRequest(userId, requestId)).thenReturn(mockDto);

        ParticipationRequestDto result = requestController.cancelRequest(userId, requestId);

        assertNotNull(result);
    }

    @Test
    public void shouldTestGetRequestInvalidUserId() {
        Long userId = -1L;

        List<ParticipationRequestDto> result = requestController.getRequest(userId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldTestCreateRequestInvalidEventId() {
        Long userId = 1L;
        Long eventId = -1L;

        Mockito.when(requestService.createRequest(userId, eventId)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            requestController.createRequest(userId, eventId);
        });
    }

    @Test
    public void shouldTestCreateRequestUserAlreadyRequested() {
        Long userId = 1L;
        Long eventId = 1L;

        Mockito.when(requestService.createRequest(userId, eventId)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            requestController.createRequest(userId, eventId);
        });
    }
}
