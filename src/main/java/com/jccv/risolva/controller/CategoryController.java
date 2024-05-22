package com.jccv.risolva.controller;

import com.jccv.risolva.dto.CategoryDto;
import com.jccv.risolva.exception.BadRequestException;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.service.CategoryService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.jccv.risolva.utils.AuthorizationsLevel.ADMIN_LEVEL;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    public ResponseEntity<?> getAllBrands() {
        try {
            return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBrandById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(categoryService.findCategoryByID(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize(ADMIN_LEVEL)
    public ResponseEntity<?> createBrand(@RequestBody CategoryDto categoryDto) {
        try {

            return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.OK);
        }catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize(ADMIN_LEVEL)
    public ResponseEntity<?> updateBrand(@RequestBody CategoryDto categoryDto, @PathVariable Long id) {
        try {
            if(categoryDto.getId() != id){
                throw new BadRequestException("your URL id is diferent than the brand you want to modify");
            }
            return new ResponseEntity<>(categoryService.updateCategory(categoryDto), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize(ADMIN_LEVEL)
    public ResponseEntity<?> deleteBrand(@PathVariable  Long id) {
        try {
            categoryService.deleteCategoryById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
