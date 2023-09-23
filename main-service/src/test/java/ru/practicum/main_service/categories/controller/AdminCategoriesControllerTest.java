package ru.practicum.main_service.categories.controller;

import org.junit.Test;
import ru.practicum.main_service.categories.dto.CategoryDto;
import ru.practicum.main_service.categories.dto.NewCategoryDto;
import ru.practicum.main_service.categories.service.CategoriesService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AdminCategoriesControllerTest {

    @Test
    public void shouldTestCreateCategoriesSSuccess() {
        NewCategoryDto newCategoryDto = mock(NewCategoryDto.class);

        CategoryDto categoryDto = mock(CategoryDto.class);

        CategoriesService categoriesService = mock(CategoriesService.class);

        when(categoriesService.createCategories(newCategoryDto)).thenReturn(categoryDto);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        CategoryDto result = adminCategoriesController.createCategories(newCategoryDto);
        assertEquals(categoryDto, result);
    }

    @Test
    public void test_createCategories_DuplicateName() {
        NewCategoryDto newCategoryDto = mock(NewCategoryDto.class);

        CategoriesService categoriesService = mock(CategoriesService.class);

        doThrow(RuntimeException.class).when(categoriesService).createCategories(newCategoryDto);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        assertThrows(RuntimeException.class, () -> adminCategoriesController.createCategories(newCategoryDto));
    }

    @Test
    public void test_deleteCategories_Success() {
        CategoriesService categoriesService = mock(CategoriesService.class);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        try {
            adminCategoriesController.deleteCategories(1L);
        } catch (Exception e) {
            fail("Exception " + e + " was thrown.");
        }
    }

    @Test
    public void test_deleteCategories_NonExistentCategory() {
        CategoriesService categoriesService = mock(CategoriesService.class);

        doThrow(RuntimeException.class).when(categoriesService).deleteCategories(1L);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        assertThrows(RuntimeException.class, () -> adminCategoriesController.deleteCategories(1L));
    }

    @Test
    public void test_updateCategories_Success() {
        CategoryDto categoryDto = mock(CategoryDto.class);

        CategoriesService categoriesService = mock(CategoriesService.class);

        when(categoriesService.updateCategories(categoryDto)).thenReturn(categoryDto);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        CategoryDto result = adminCategoriesController.updateCategories(1L, categoryDto);
        assertEquals(categoryDto, result);
    }

    @Test
    public void test_updateCategories_InvalidInput() {
        CategoryDto categoryDto = mock(CategoryDto.class);

        CategoriesService categoriesService = mock(CategoriesService.class);

        when(categoriesService.updateCategories(categoryDto)).thenThrow(RuntimeException.class);

        AdminCategoriesController adminCategoriesController = new AdminCategoriesController(categoriesService);

        assertThrows(RuntimeException.class, () -> adminCategoriesController.updateCategories(1L, categoryDto));
    }
}