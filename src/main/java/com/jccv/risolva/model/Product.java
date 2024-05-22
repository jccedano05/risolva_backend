package com.jccv.risolva.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    private String urlImage;
    private String nameImage;
    private String description;
    private Double price;
    private Integer quantity;



    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category must not be empty")
    private Category category;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brandy_id", nullable = false)
    @NotNull(message = "Brand must not be empty")
    private Brand brand;

}
