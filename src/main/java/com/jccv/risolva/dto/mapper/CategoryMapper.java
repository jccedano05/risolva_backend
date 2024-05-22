package com.jccv.risolva.dto.mapper;

import com.jccv.risolva.dto.CategoryDto;
import com.jccv.risolva.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto convertModelToDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }


    public Category convertDtoModel(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
    }
}
