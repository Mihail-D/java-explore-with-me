package ru.practicum.main_service.compilations.service;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.practicum.main_service.compilations.dto.CompilationDto;
import ru.practicum.main_service.compilations.dto.NewCompilationDto;
import ru.practicum.main_service.compilations.model.Compilation;
import ru.practicum.main_service.compilations.repository.CompilationRepository;
import ru.practicum.main_service.event.repository.EventRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CompilationServiceImplTest {

    @Test
    public void test_retrieve_all_compilations_no_filters() {
        CompilationRepository compilationRepository = mock(CompilationRepository.class);
        EventRepository eventRepository = mock(EventRepository.class);
        CompilationService compilationService = new CompilationServiceImpl(compilationRepository, eventRepository);

        List<Compilation> compilations = new ArrayList<>();
        Compilation compilation = new Compilation();
        compilation.setEvents(new ArrayList<>());
        compilations.add(compilation);

        Page<Compilation> page = new PageImpl<>(compilations);

        when(compilationRepository.findAll(any(PageRequest.class))).thenReturn(page);

        List<CompilationDto> result = compilationService.getAllCompilations(null, 0, 10);

        assertEquals(compilations.size(), result.size());
    }


    @Test
    public void test_create_new_compilation_no_events() {
        CompilationRepository compilationRepository = mock(CompilationRepository.class);
        EventRepository eventRepository = mock(EventRepository.class);
        CompilationService compilationService = new CompilationServiceImpl(compilationRepository, eventRepository);

        NewCompilationDto newCompilationDto = new NewCompilationDto();

        Compilation compilation = new Compilation();

        when(compilationRepository.save(any(Compilation.class))).thenReturn(compilation);

        CompilationDto result = compilationService.createCompilation(newCompilationDto);

        assertNotNull(result);
    }


    @Test
    public void test_create_new_compilation_invalid_event_ids() {
        CompilationRepository compilationRepository = mock(CompilationRepository.class);
        EventRepository eventRepository = mock(EventRepository.class);
        CompilationService compilationService = new CompilationServiceImpl(compilationRepository, eventRepository);

        NewCompilationDto newCompilationDto = new NewCompilationDto();
        newCompilationDto.setEvents(Arrays.asList(1L, 2L));

        Compilation compilation = new Compilation();

        when(compilationRepository.save(any(Compilation.class))).thenReturn(compilation);
        when(eventRepository.findAllById(eq(newCompilationDto.getEvents()))).thenReturn(Collections.emptyList());

        CompilationDto result = compilationService.createCompilation(newCompilationDto);

        assertNotNull(result);
    }

}