package com.jccv.risolva.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BrandDto {

    private Long id;

    private String name;
    private String description;
}
