package com.jccv.risolva.repository.facade;

import com.jccv.risolva.dto.CategoryDto;
import com.jccv.risolva.dto.mapper.CategoryMapper;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.model.Category;
import com.jccv.risolva.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryFacade {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    public CategoryDto findCategoryById (Long id){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No category founded with Id: " + id));
        return categoryMapper.convertModelToDto(category);
    }

    public List<CategoryDto> findAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> categoryMapper.convertModelToDto(category)).collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = categoryMapper.convertDtoModel(categoryDto);
        category = categoryRepository.save(category);
        return categoryMapper.convertModelToDto(category);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {

        if (categoryDto.getId() != null && categoryRepository.existsById(categoryDto.getId())) {
            Category category = categoryRepository.save(categoryMapper.convertDtoModel(categoryDto));
            return categoryMapper.convertModelToDto(category);
        } else {
            throw new ResourceNotFoundException("No category founded with Id: " + categoryDto.getId());
        }
    }

    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No category founded with Id: " + id);
        }
    }



}
