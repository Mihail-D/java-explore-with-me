package ru.practicum.main_service.categories.service;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.practicum.main_service.categories.dto.CategoryDto;
import ru.practicum.main_service.categories.dto.NewCategoryDto;
import ru.practicum.main_service.categories.model.Categories;
import ru.practicum.main_service.categories.repository.CategoriesRepository;
import ru.practicum.main_service.event.repository.EventRepository;
import ru.practicum.main_service.exception.ConflictException;
import ru.practicum.main_service.exception.ObjectNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CategoriesServiceImplTest {

    @Test
    public void shouldTestGetCategories() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(Categories.builder().id(1L).name("Category 1").build());
        categoriesList.add(Categories.builder().id(2L).name("Category 2").build());

        Mockito.when(categoriesRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(categoriesList));

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        List<CategoryDto> result = categoriesService.getCategories(0, 10);

        assertEquals(2, result.size());
        assertEquals("Category 1", result.get(0).getName());
        assertEquals("Category 2", result.get(1).getName());
    }

    @Test
    public void shouldTestGetCategoriesId() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        Categories category = Categories.builder().id(1L).name("Category 1").build();

        Mockito.when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(category));

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        CategoryDto result = categoriesService.getCategoriesId(1L);

        assertEquals("Category 1", result.getName());
    }

    @Test
    public void shouldTestCreateCategories() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        NewCategoryDto newCategoryDto = NewCategoryDto.builder().name("Category 1").build();
        Categories createdCategory = Categories.builder().id(1L).name("Category 1").build();

        Mockito.when(categoriesRepository.existsCategoriesByName(Mockito.anyString())).thenReturn(false);
        Mockito.when(categoriesRepository.save(Mockito.any(Categories.class))).thenReturn(createdCategory);

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        CategoryDto result = categoriesService.createCategories(newCategoryDto);

        assertEquals("Category 1", result.getName());
    }

    @Test
    public void shouldTestGetCategoriesNullFromAndSize() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        List<Categories> categoriesList = new ArrayList<>();
        categoriesList.add(Categories.builder().id(1L).name("Category 1").build());

        Mockito.when(categoriesRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(new PageImpl<>(categoriesList));

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        Integer from = null;
        Integer size = null;

        from = 0;
        size = 10;

        List<CategoryDto> result = categoriesService.getCategories(from, size);

        assertEquals(1, result.size());
        assertEquals("Category 1", result.get(0).getName());
    }


    @Test
    public void shouldTestGetCategoriesIdCategoryNotFound() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        Mockito.when(categoriesRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        assertThrows(ObjectNotFoundException.class, () -> categoriesService.getCategoriesId(1L));
    }

    @Test
    public void shouldTestCreateCategoriesCategoryAlreadyExists() {
        CategoriesRepository categoriesRepository = Mockito.mock(CategoriesRepository.class);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);

        NewCategoryDto newCategoryDto = NewCategoryDto.builder().name("Category 1").build();

        Mockito.when(categoriesRepository.existsCategoriesByName(Mockito.anyString())).thenReturn(true);

        CategoriesServiceImpl categoriesService = new CategoriesServiceImpl(categoriesRepository, eventRepository);

        assertThrows(ConflictException.class, () -> categoriesService.createCategories(newCategoryDto));
    }
}
