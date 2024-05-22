package com.jccv.risolva.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDto {
    private Long id;

    private String name;
    private String description;
    private ImageDto image;
    private Double price;
    private Integer quantity;

    private CategoryDto category;

    private BrandDto brand;
}
