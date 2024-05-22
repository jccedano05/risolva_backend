package com.jccv.risolva.dto.mapper;

import com.jccv.risolva.dto.BrandDto;
import com.jccv.risolva.dto.CategoryDto;
import com.jccv.risolva.dto.ImageDto;
import com.jccv.risolva.dto.ProductDto;
import com.jccv.risolva.model.Product;
import com.jccv.risolva.repository.facade.BrandFacade;
import com.jccv.risolva.repository.facade.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private BrandFacade brandFacade;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryFacade categoryFacade;
    @Autowired
    private CategoryMapper categoryMapper;

    public ProductDto convertModelToDto(Product product){


        ProductDto productDto =  ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .image(ImageDto.builder()
                        .url(product.getUrlImage())
                        .name(product.getNameImage())
                        .build())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();

        if(product.getBrand() != null){
            BrandDto brandDto = brandMapper.convertModelToDto(product.getBrand());
            productDto.setBrand(brandDto);
        }
        if(product.getCategory() != null){

            productDto.setCategory(categoryMapper.convertModelToDto(product.getCategory()));
        }
        return productDto;
    }

    public Product convertDtoToModel(ProductDto productDto){

        var product = Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();

        if(productDto.getImage() != null){
            product.setUrlImage(productDto.getImage().getUrl());
            product.setNameImage(productDto.getImage().getName());
        }

        if(productDto.getBrand() != null){

            product.setBrand(brandMapper.convertDtoModel(productDto.getBrand()));
        }

        if(productDto.getCategory() != null){
            product.setCategory(categoryMapper.convertDtoModel(productDto.getCategory()));
        }
        return product;
    }

}
