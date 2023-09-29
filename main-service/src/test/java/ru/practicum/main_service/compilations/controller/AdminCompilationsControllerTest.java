package ru.practicum.main_service.compilations.controller;

import org.junit.Test;
import ru.practicum.main_service.compilations.dto.CompilationDto;
import ru.practicum.main_service.compilations.dto.NewCompilationDto;
import ru.practicum.main_service.compilations.dto.UpdateCompilationRequest;
import ru.practicum.main_service.compilations.service.CompilationService;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class AdminCompilationsControllerTest {

    @Test
    public void shouldCreateCompilationWithValidInput() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .title("Test Compilation")
                .build();
        CompilationDto expectedCompilation = CompilationDto.builder()
                .id(1L)
                .title("Test Compilation")
                .build();
        when(compilationService.createCompilation(newCompilationDto)).thenReturn(expectedCompilation);

        CompilationDto actualCompilation = controller.createCompilation(newCompilationDto);

        assertEquals(expectedCompilation, actualCompilation);
        verify(compilationService).createCompilation(newCompilationDto);
    }

    @Test
    public void shouldDeleteExistingCompilationWithValidInput() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        Long compId = 1L;

        controller.deleteCompilation(compId);

        verify(compilationService).deleteCompilation(compId);
    }

    @Test
    public void shouldUpdateExistingCompilationWithValidInput() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        Long compId = 1L;
        UpdateCompilationRequest request = UpdateCompilationRequest.builder()
                .title("Updated Compilation")
                .build();
        CompilationDto expectedCompilation = CompilationDto.builder()
                .id(compId)
                .title("Updated Compilation")
                .build();
        when(compilationService.updateCompilation(compId, request)).thenReturn(expectedCompilation);

        CompilationDto actualCompilation = controller.updateCompilation(compId, request);

        assertEquals(expectedCompilation, actualCompilation);
        verify(compilationService).updateCompilation(compId, request);
    }

    @Test
    public void shouldCreateCompilationWithInvalidInput() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .title("")
                .build();

        doThrow(ConstraintViolationException.class).when(compilationService).createCompilation(any(NewCompilationDto.class));

        assertThrows(ConstraintViolationException.class, () -> controller.createCompilation(newCompilationDto));
    }



    @Test
    public void shouldDeleteNonexistentCompilation() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        Long compId = 1L;
        doThrow(new IllegalArgumentException("Compilation not found with id: " + compId)).when(compilationService).deleteCompilation(compId);

        assertThrows(IllegalArgumentException.class, () -> controller.deleteCompilation(compId));

        verify(compilationService).deleteCompilation(compId);
    }

    @Test
    public void shouldUpdateNonexistentCompilation() {
        CompilationService compilationService = mock(CompilationService.class);
        AdminCompilationsController controller = new AdminCompilationsController(compilationService);
        Long compId = 1L;
        UpdateCompilationRequest request = UpdateCompilationRequest.builder()
                .title("Updated Compilation")
                .build();
        doThrow(new IllegalArgumentException("Compilation not found with id: " + compId)).when(compilationService).updateCompilation(compId, request);

        assertThrows(IllegalArgumentException.class, () -> controller.updateCompilation(compId, request));

        verify(compilationService).updateCompilation(compId, request);
    }
}