package com.jccv.risolva.controller;

import com.jccv.risolva.dto.ProductDto;
import com.jccv.risolva.exception.BadRequestException;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.service.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.jccv.risolva.utils.AuthorizationsLevel.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping()
    public ResponseEntity<?> getAllBrands() {
        try {
            return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("brands/{brandId}")
    public ResponseEntity<?> getAllProductsByBrandId(@PathVariable Long brandId) {
        try {
            return new ResponseEntity<>(productService.findAllProductsByBrandId(brandId), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<?> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        try {
            return new ResponseEntity<>(productService.findAllProductsByCategoryID(categoryId), HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize(ADMIN_LEVEL)
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            return new ResponseEntity<>(productService.create(productDto), HttpStatus.CREATED);
        }catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    @PreAuthorize(ADMIN_LEVEL)
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long id) {
        try {
            if(productDto.getId() != id){
                throw new BadRequestException("your URL id is diferent than the brand you want to modify");
            }
            return new ResponseEntity<>(productService.update(productDto), HttpStatus.OK);
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
    public ResponseEntity<?> deleteProduct(@PathVariable  Long id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>( HttpStatus.OK);
        }catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
