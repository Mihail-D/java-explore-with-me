package ru.practicum.main_service.compilations.dto;

import org.junit.Test;

import javax.validation.*;
import java.util.*;

import static org.junit.Assert.*;

public class NewCompilationDtoTest {

    @Test
    public void shouldTestValidParameters() {
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .pinned(true)
                .title("Valid Title")
                .events(Arrays.asList(1L, 2L, 3L))
                .build();

        assertTrue(newCompilationDto.getPinned());
        assertEquals("Valid Title", newCompilationDto.getTitle());
        assertEquals(Arrays.asList(1L, 2L, 3L), newCompilationDto.getEvents());
    }

    @Test
    public void shouldTestNonNullEventsList() {
        List<Long> events = Arrays.asList(1L, 2L, 3L);
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .events(events)
                .build();

        assertEquals(events, newCompilationDto.getEvents());
    }

    @Test
    public void shouldTestNullTitle() {
        assertThrows(ConstraintViolationException.class, () -> {
            NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                    .title(null)
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<NewCompilationDto>> violations = validator.validate(newCompilationDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldTestExceedMaxLengthTitle() {
        assertThrows(ConstraintViolationException.class, () -> {
            NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                    .title("This title exceeds the maximum length allowed by a significant margin and should cause a validation exception")
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<NewCompilationDto>> violations = validator.validate(newCompilationDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldTestEmptyEventsList() {
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .events(Collections.emptyList())
                .build();

        assertEquals(Collections.emptyList(), newCompilationDto.getEvents());
    }

    @Test
    public void shouldTestDefaultPinnedValue() {
        NewCompilationDto newCompilationDto = NewCompilationDto.builder()
                .build();

        assertFalse(newCompilationDto.getPinned());
    }

}