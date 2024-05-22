package com.jccv.risolva.service;

import com.jccv.risolva.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public CategoryDto findCategoryByID (Long id);
    public List<CategoryDto> findAllCategories();
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public void deleteCategoryById(Long id);
}
