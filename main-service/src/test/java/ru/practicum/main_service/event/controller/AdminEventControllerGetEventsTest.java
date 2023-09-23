package ru.practicum.main_service.event.controller;

import org.junit.Test;
import ru.practicum.main_service.event.dto.EventFullDto;
import ru.practicum.main_service.event.model.State;
import ru.practicum.main_service.event.service.EventService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AdminEventControllerGetEventsTest {

    @Test
    public void test_behaviour_aaa() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(null, null, null, null, null, 0, 10)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(null, null, null, null, null, 0, 10);

        assertEquals(expected, result);
    }

    @Test
    public void test_behaviour_bbb() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<Long> users = Arrays.asList(1L, 2L);
        List<State> states = Arrays.asList(State.PENDING, State.CONFIRMED);
        List<Long> categories = Arrays.asList(1L, 2L);
        String rangeStart = "2022-01-01";
        String rangeEnd = "2022-01-31";
        Integer from = 0;
        Integer size = 10;
        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size);

        assertEquals(expected, result);
    }

    @Test
    public void test_behaviour_ccc() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<Long> users = Arrays.asList(1L, 2L);
        List<State> states = null;
        List<Long> categories = Arrays.asList(1L, 2L);
        String rangeStart = null;
        String rangeEnd = "2022-01-31";
        Integer from = 0;
        Integer size = 10;
        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size);

        assertEquals(expected, result);
    }

    @Test
    public void test_behaviour_ddd() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<Long> users = Arrays.asList(1L, 2L);
        List<State> states = Arrays.asList(State.PUBLISHED);
        List<Long> categories = Arrays.asList(1L, 2L);
        String rangeStart = "2022-01-01";
        String rangeEnd = "2022-01-31";
        Integer from = 0;
        Integer size = 10;
        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size);

        assertEquals(expected, result);
    }

    @Test
    public void test_behaviour_eee() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<Long> users = null;
        List<State> states = null;
        List<Long> categories = Arrays.asList(1L, 2L);
        String rangeStart = null;
        String rangeEnd = null;
        Integer from = 0;
        Integer size = 10;
        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size);

        assertEquals(expected, result);
    }

    @Test
    public void test_behaviour_fff() {
        EventService eventService = mock(EventService.class);
        AdminEventController adminEventController = new AdminEventController(eventService);

        List<Long> users = null;
        List<State> states = null;
        List<Long> categories = null;
        String rangeStart = "2022-01-01";
        String rangeEnd = "2022-01-01";
        Integer from = 0;
        Integer size = 10;
        List<EventFullDto> expected = new ArrayList<>();
        when(eventService.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size)).thenReturn(expected);

        List<EventFullDto> result = adminEventController.adminGetEvents(users, states, categories, rangeStart, rangeEnd, from, size);

        assertEquals(expected, result);
    }
}
