package com.jccv.risolva.service.impl;

import com.jccv.risolva.dto.ProductDto;
import com.jccv.risolva.repository.facade.ProductFacade;
import com.jccv.risolva.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductFacade productFacade;
    @Override
    public ProductDto findById(Long id) {
        return productFacade.findById(id);
    }

    @Override
    public List<ProductDto> findAllProducts() {
        return productFacade.findAllProducts();
    }

    @Override
    public List<ProductDto> findAllProductsByCategoryID(Long categoryId) {
        return productFacade.findAllProductsByCategoryId(categoryId);
    }

    @Override
    public List<ProductDto> findAllProductsByBrandId(Long brandId) {
        return productFacade.findAllProductsByBrandId(brandId);
    }

    @Override
    public ProductDto create(ProductDto productDto) {
        return productFacade.createProduct(productDto);
    }

    @Override
    public ProductDto update(ProductDto productDto) {
        return productFacade.updateProduct(productDto);
    }

    @Override
    public void deleteById(Long id) {
        productFacade.deleteBrandById(id);
    }
}
