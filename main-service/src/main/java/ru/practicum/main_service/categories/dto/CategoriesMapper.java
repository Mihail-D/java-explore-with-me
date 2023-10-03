package ru.practicum.main_service.categories.dto;

import lombok.experimental.UtilityClass;
import ru.practicum.main_service.categories.model.Categories;

@UtilityClass
public class CategoriesMapper {

    public CategoryDto toCategoryDto(Categories categories) {
        if (categories == null) {
            return CategoryDto.builder()
                    .id(null)
                    .name(null)
                    .build();
        }
        return CategoryDto.builder()
                .id(categories.getId())
                .name(categories.getName())
                .build();
    }

    public Categories toCategories(NewCategoryDto newCategoryDto) {
        if (newCategoryDto == null) {
            return Categories.builder()
                    .name(null)
                    .build();
        }
        return Categories.builder()
                .name(newCategoryDto.getName())
                .build();
    }
}