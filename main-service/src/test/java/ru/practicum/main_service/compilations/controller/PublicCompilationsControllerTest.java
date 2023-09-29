package ru.practicum.main_service.compilations.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.practicum.main_service.compilations.dto.CompilationDto;
import ru.practicum.main_service.compilations.repository.CompilationRepository;
import ru.practicum.main_service.compilations.service.CompilationService;
import ru.practicum.main_service.compilations.service.CompilationServiceImpl;
import ru.practicum.main_service.event.repository.EventRepository;

import java.util.List;

import static org.junit.Assert.*;

public class PublicCompilationsControllerTest {

    private CompilationService compilationsService;
    private CompilationRepository compilationsRepository;
    private EventRepository eventRepository;

    @Before
    public void setup() {
        compilationsRepository = Mockito.mock(CompilationRepository.class);
        eventRepository = Mockito.mock(EventRepository.class);

        compilationsService = new CompilationServiceImpl(compilationsRepository, eventRepository);
    }

    @Test
    public void shouldGetAllCompilationsValidParameters() {
        Boolean pinned = true;
        Integer from = 0;
        Integer size = 10;

        List<CompilationDto> result = compilationsService.getAllCompilations(pinned, from, size);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void shouldGetAllCompilationsNoPinnedCompilations() {
        Boolean pinned = false;
        Integer from = 0;
        Integer size = 10;

        List<CompilationDto> result = compilationsService.getAllCompilations(pinned, from, size);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldGetCompilationsByIdInvalidId() {
        Long compId = -1L;

        assertThrows(Exception.class, () -> compilationsService.getCompilationsById(compId));
    }

}

