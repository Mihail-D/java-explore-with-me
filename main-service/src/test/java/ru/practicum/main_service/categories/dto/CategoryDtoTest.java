package ru.practicum.main_service.categories.dto;

import org.junit.Test;

import javax.validation.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryDtoTest {

    @Test
    public void shouldTestCreateCategoryDtoWithValidParameters() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name("Valid Name")
                .build();

        assertNotNull(categoryDto);
        assertEquals(1L, categoryDto.getId().longValue());
        assertEquals("Valid Name", categoryDto.getName());
    }

    @Test
    public void shouldTestCreateCategoryDtoWithNameOf50Characters() {
        String name = "12345678901234567890123456789012345678901234567890";
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name(name)
                .build();

        assertNotNull(categoryDto);
        assertEquals(1L, categoryDto.getId().longValue());
        assertEquals(name, categoryDto.getName());
    }

    @Test
    public void shouldTestCreateCategoryDtoWithNameOf1Character() {
        String name = "A";
        CategoryDto categoryDto = CategoryDto.builder()
                .id(1L)
                .name(name)
                .build();

        assertNotNull(categoryDto);
        assertEquals(1L, categoryDto.getId().longValue());
        assertEquals(name, categoryDto.getName());
    }

    @Test
    public void shouldTestCreateCategoryDtoWithNullName() {
        assertThrows(ConstraintViolationException.class, () -> {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(1L)
                    .name(null)
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CategoryDto>> violations = validator.validate(categoryDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldTestCreateCategoryDtoWithEmptyName() {
        assertThrows(ConstraintViolationException.class, () -> {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(1L)
                    .name("")
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CategoryDto>> violations = validator.validate(categoryDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }

    @Test
    public void shouldTestCreateCategoryDtoWithNameOf51Characters() {
        String name = "123456789012345678901234567890123456789012345678901";
        assertThrows(ConstraintViolationException.class, () -> {
            CategoryDto categoryDto = CategoryDto.builder()
                    .id(1L)
                    .name(name)
                    .build();

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<CategoryDto>> violations = validator.validate(categoryDto);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
            }
        });
    }
}