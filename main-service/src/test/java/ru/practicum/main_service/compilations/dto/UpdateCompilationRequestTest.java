package ru.practicum.main_service.compilations.dto;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UpdateCompilationRequestTest {

    @Test
    public void shouldTestConstructorInitializesFieldsCorrectly() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();
        assertNotNull(request.getEvents());
        assertTrue(request.getEvents().isEmpty());
        assertNull(request.getPinned());
        assertNull(request.getTitle());
    }

    @Test
    public void shouldTestGetterAndSetterMethods() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();

        List<Long> events = new ArrayList<>();
        events.add(1L);
        request.setEvents(events);
        assertEquals(events, request.getEvents());

        Boolean pinned = true;
        request.setPinned(pinned);
        assertEquals(pinned, request.getPinned());

        String title = "Test Title";
        request.setTitle(title);
        assertEquals(title, request.getTitle());
    }

    @Test
    public void shouldTestSetEventsNonEmptyList() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();

        List<Long> events = new ArrayList<>();
        events.add(1L);
        events.add(2L);
        request.setEvents(events);

        assertEquals(events, request.getEvents());
    }

    @Test
    public void shouldTestSetEventsEmptyList() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();

        List<Long> events = new ArrayList<>();
        request.setEvents(events);

        assertEquals(events, request.getEvents());
    }

    @Test
    public void shouldTestSetPinnedNull() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();

        request.setPinned(null);

        assertNull(request.getPinned());
    }

    @Test
    public void shouldTestSetTitleOneCharacter() {
        UpdateCompilationRequest request = new UpdateCompilationRequest();

        String title = "A";
        request.setTitle(title);

        assertEquals(title, request.getTitle());
    }
}