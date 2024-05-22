package com.jccv.risolva.repository;

import com.jccv.risolva.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.beans.JavaBean;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryID")
    Optional<List<Product>> findAllProductsByCategoryId(@Param("categoryID") Long categoryID);


    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId")
    Optional<List<Product>> findAllProductsByBrandId(@Param("brandId") Long brandId);
}
