package com.jccv.risolva.service.impl;

import com.jccv.risolva.dto.CategoryDto;
import com.jccv.risolva.repository.facade.CategoryFacade;
import com.jccv.risolva.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryFacade categoryFacade;

    @Override
    public CategoryDto findCategoryByID(Long id) {
        return categoryFacade.findCategoryById(id);
    }

    @Override
    public List<CategoryDto> findAllCategories() {
        return categoryFacade.findAllCategories();
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return categoryFacade.createCategory(categoryDto);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return categoryFacade.updateCategory(categoryDto);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryFacade.deleteCategoryById(id);
    }
}
