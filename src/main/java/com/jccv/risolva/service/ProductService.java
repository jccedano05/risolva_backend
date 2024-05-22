package com.jccv.risolva.service;

import com.jccv.risolva.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public ProductDto findById (Long id);
    public List<ProductDto> findAllProducts();
    public List<ProductDto> findAllProductsByCategoryID(Long id);
    public List<ProductDto> findAllProductsByBrandId(Long id);
    public ProductDto create(ProductDto productDto);
    public ProductDto update(ProductDto productDto);
    public void deleteById(Long id);
}
