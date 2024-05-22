package com.jccv.risolva.repository.facade;

import com.jccv.risolva.dto.ProductDto;
import com.jccv.risolva.dto.mapper.ProductMapper;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.model.Product;
import com.jccv.risolva.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFacade {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public ProductDto findById (Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No product founded with Id: " + id));
        return productMapper.convertModelToDto(product);
    }

    public List<ProductDto> findAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> productMapper.convertModelToDto(product)).collect(Collectors.toList());
    }

    public List<ProductDto> findAllProductsByCategoryId(long categoryId){
        List<Product> products = productRepository.findAllProductsByCategoryId(categoryId).orElseThrow(() -> new ResourceNotFoundException("No product founded with categoryId: " + categoryId));
        return products.stream().map(product -> productMapper.convertModelToDto(product)).collect(Collectors.toList());
    }

    public List<ProductDto> findAllProductsByBrandId(Long brandId){
        List<Product> products = productRepository.findAllProductsByBrandId(brandId).orElseThrow(() -> new ResourceNotFoundException("No product founded with brandId: " + brandId));
        return products.stream().map(product -> productMapper.convertModelToDto(product)).collect(Collectors.toList());
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.convertDtoToModel(productDto);
        product = productRepository.save(product);
        return productMapper.convertModelToDto(product);
    }

    public ProductDto updateProduct(ProductDto productDto) {

        if (productDto.getId() != null && productRepository.existsById(productDto.getId())) {
            Product product = productRepository.save(productMapper.convertDtoToModel(productDto));
            return productMapper.convertModelToDto(product);
        } else {
            throw new ResourceNotFoundException("No ProductDto founded with Id: " + productDto.getId());
        }
    }

    public void deleteBrandById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No Product founded with Id: " + id);
        }
    }

}
