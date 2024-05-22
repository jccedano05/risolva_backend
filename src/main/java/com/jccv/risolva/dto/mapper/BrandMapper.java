package com.jccv.risolva.dto.mapper;

import com.jccv.risolva.dto.BrandDto;
import com.jccv.risolva.model.Brand;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    public BrandDto convertModelToDto(Brand brand){
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .build();
    }


    public Brand convertDtoModel(BrandDto brandDto){
        return Brand.builder()
                .id(brandDto.getId())
                .name(brandDto.getName())
                .description(brandDto.getDescription())
                .build();
    }
}
