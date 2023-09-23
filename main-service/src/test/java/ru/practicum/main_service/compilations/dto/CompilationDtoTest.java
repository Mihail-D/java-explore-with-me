package ru.practicum.main_service.compilations.dto;

import org.junit.Test;
import ru.practicum.main_service.event.dto.EventShortDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompilationDtoTest {

    @Test
    public void shouldInstantiateWithDefaultConstructor() {
        CompilationDto compilationDto = new CompilationDto();
        assertNotNull(compilationDto);
    }

    @Test
    public void shouldInstantiateWithAllArgsConstructor() {
        Long id = 1L;
        Boolean pinned = true;
        String title = "Test Compilation";
        List<EventShortDto> events = new ArrayList<>();

        CompilationDto compilationDto = new CompilationDto(id, pinned, title, events);
        assertNotNull(compilationDto);
        assertEquals(id, compilationDto.getId());
        assertEquals(pinned, compilationDto.getPinned());
        assertEquals(title, compilationDto.getTitle());
        assertEquals(events, compilationDto.getEvents());
    }

    @Test
    public void shouldInstantiateWithBuilder() {
        Long id = 1L;
        Boolean pinned = true;
        String title = "Test Compilation";
        List<EventShortDto> events = new ArrayList<>();

        CompilationDto compilationDto = CompilationDto.builder()
                .id(id)
                .pinned(pinned)
                .title(title)
                .events(events)
                .build();

        assertNotNull(compilationDto);
        assertEquals(id, compilationDto.getId());
        assertEquals(pinned, compilationDto.getPinned());
        assertEquals(title, compilationDto.getTitle());
        assertEquals(events, compilationDto.getEvents());
    }

    @Test
    public void shouldTestNullIdField() {
        Boolean pinned = true;
        String title = "Test Compilation";
        List<EventShortDto> events = new ArrayList<>();

        CompilationDto compilationDto = CompilationDto.builder()
                .pinned(pinned)
                .title(title)
                .events(events)
                .build();

        assertNotNull(compilationDto);
        assertNull(compilationDto.getId());
        assertEquals(pinned, compilationDto.getPinned());
        assertEquals(title, compilationDto.getTitle());
        assertEquals(events, compilationDto.getEvents());
    }

    @Test
    public void shouldTestNullPinnedField() {
        Long id = 1L;
        String title = "Test Compilation";
        List<EventShortDto> events = new ArrayList<>();

        CompilationDto compilationDto = CompilationDto.builder()
                .id(id)
                .title(title)
                .events(events)
                .build();

        assertNotNull(compilationDto);
        assertEquals(id, compilationDto.getId());
        assertNull(compilationDto.getPinned());
        assertEquals(title, compilationDto.getTitle());
        assertEquals(events, compilationDto.getEvents());
    }

    @Test
    public void shouldTestNullTitleField() {
        Long id = 1L;
        Boolean pinned = true;
        List<EventShortDto> events = new ArrayList<>();

        CompilationDto compilationDto = CompilationDto.builder()
                .id(id)
                .pinned(pinned)
                .events(events)
                .build();

        assertNotNull(compilationDto);
        assertEquals(id, compilationDto.getId());
        assertEquals(pinned, compilationDto.getPinned());
        assertNull(compilationDto.getTitle());
        assertEquals(events, compilationDto.getEvents());
    }
}