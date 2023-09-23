package ru.practicum.main_service.categories.dto;

import org.junit.Test;

import javax.validation.*;

import java.util.Set;

import static org.junit.Assert.*;

public class NewCategoryDtoTest {

    @Test
    public void shouldTestNonEmptyName() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("category")
                .build();
        assertNotNull(newCategoryDto);
        assertEquals("category", newCategoryDto.getName());
    }

    @Test
    public void shouldTestNameLength1() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("a")
                .build();
        assertNotNull(newCategoryDto);
        assertEquals("a", newCategoryDto.getName());
    }

    @Test
    public void shouldTestNameLength50() {
        String name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name(name)
                .build();
        assertNotNull(newCategoryDto);
        assertEquals(name, newCategoryDto.getName());
    }

    @Test
    public void shouldTestNameLength0() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("")
                .build();
        assertNotNull(newCategoryDto);
        assertEquals("", newCategoryDto.getName());
    }

    @Test
    public void shouldTestNameLength51() {
        String name = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz1";
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name(name)
                .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<NewCategoryDto>> violations = validator.validate(newCategoryDto);

        assertFalse(violations.isEmpty());
    }


    @Test
    public void shouldTestNameWhitespace() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("   ")
                .build();
        assertNotNull(newCategoryDto);
        assertEquals("   ", newCategoryDto.getName());
    }
}