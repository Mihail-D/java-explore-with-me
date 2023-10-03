package ru.practicum.main_service.categories.dto;

import org.junit.Test;
import ru.practicum.main_service.categories.model.Categories;

import static org.junit.Assert.assertEquals;

public class CategoriesMapperTest {

    @Test
    public void shouldTestToCategoryDtoReturnsCorrectCategoryDto() {
        Categories categories = new Categories(1, "Test Category");
        CategoryDto expected = CategoryDto.builder()
                .id(1L)
                .name("Test Category")
                .build();

        CategoryDto result = CategoriesMapper.toCategoryDto(categories);

        assertEquals(expected, result);
    }

    @Test
    public void shouldToCategoriesReturnsCorrectCategories() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("Test Category")
                .build();

        Categories expected = Categories.builder()
                .name("Test Category")
                .build();

        Categories result = CategoriesMapper.toCategories(newCategoryDto);

        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void shouldToCategoryDtoReturnsCategoryDtoWithNullFieldsWhenGivenNullCategories() {
        Categories categories = null;
        CategoryDto expected = CategoryDto.builder()
                .id(null)
                .name(null)
                .build();

        CategoryDto result = CategoriesMapper.toCategoryDto(categories);

        assertEquals(expected, result);
    }

    @Test
    public void shouldToCategoriesReturnsCategoriesWithNullNameFieldWhenGivenNullNewCategoryDto() {
        NewCategoryDto newCategoryDto = null;
        Categories expected = Categories.builder()
                .name(null)
                .build();

        Categories result = CategoriesMapper.toCategories(newCategoryDto);

        assertEquals(expected.getName(), result.getName());
    }

    @Test
    public void shouldToCategoryDtoReturnsCategoryDtoWithSpecialCharactersInName() {
        Categories categories = new Categories(1, "Test Category$");
        CategoryDto expected = CategoryDto.builder()
                .id(1L)
                .name("Test Category$")
                .build();

        CategoryDto result = CategoriesMapper.toCategoryDto(categories);

        assertEquals(expected, result);
    }

    @Test
    public void shouldToCategoriesReturnsCategoriesWithSpecialCharactersInName() {
        NewCategoryDto newCategoryDto = NewCategoryDto.builder()
                .name("Test Category$")
                .build();

        Categories expected = Categories.builder()
                .name("Test Category$")
                .build();

        Categories result = CategoriesMapper.toCategories(newCategoryDto);

        assertEquals(expected.getName(), result.getName());
    }
}