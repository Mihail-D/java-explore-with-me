package ru.practicum.main_service.compilations.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.main_service.compilations.dto.CompilationDto;
import ru.practicum.main_service.compilations.service.CompilationService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PublicCompilationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOkStatusWhenGetAllCompilationsIsCalled() throws Exception {
        this.mockMvc.perform(get("/compilations"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnListOfCompilationDtoObjectsWhenValidParametersArePassed() {
        CompilationService compilationService = mock(CompilationService.class);
        List<CompilationDto> expectedCompilations = new ArrayList<>();
        CompilationDto compilation1 = new CompilationDto();
        CompilationDto compilation2 = new CompilationDto();
        expectedCompilations.add(compilation1);
        expectedCompilations.add(compilation2);
        when(compilationService.getAllCompilations(anyBoolean(), anyInt(), anyInt())).thenReturn(expectedCompilations);

        PublicCompilationsController controller = new PublicCompilationsController(compilationService);

        List<CompilationDto> actualCompilations = controller.getAllCompilations(true, 0, 10);

        Assertions.assertEquals(expectedCompilations, actualCompilations);
    }

    @Test
    public void shouldReturnEmptyListWhenNoCompilationsAreFound() {
        CompilationService compilationService = mock(CompilationService.class);
        List<CompilationDto> expectedCompilations = new ArrayList<>();
        when(compilationService.getAllCompilations(anyBoolean(), anyInt(), anyInt())).thenReturn(expectedCompilations);

        PublicCompilationsController controller = new PublicCompilationsController(compilationService);

        List<CompilationDto> actualCompilations = controller.getAllCompilations(null, 0, 10);

        Assertions.assertEquals(expectedCompilations, actualCompilations);
    }

    @Test
    public void shouldReturnEmptyListWhenFromParameterIsGreaterThanNumberOfCompilations() {
        CompilationService compilationService = mock(CompilationService.class);
        List<CompilationDto> expectedCompilations = new ArrayList<>();
        when(compilationService.getAllCompilations(anyBoolean(), anyInt(), anyInt())).thenReturn(expectedCompilations);

        PublicCompilationsController controller = new PublicCompilationsController(compilationService);

        List<CompilationDto> actualCompilations = controller.getAllCompilations(null, 10, null);

        Assertions.assertEquals(expectedCompilations, actualCompilations);
    }
}
