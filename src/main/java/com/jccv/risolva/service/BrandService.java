package com.jccv.risolva.service;

import com.jccv.risolva.dto.BrandDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {

    public BrandDto findBrandByID (Long id);
    public List<BrandDto> findAllBrands();
    public BrandDto createBrand(BrandDto brandDto);
    public BrandDto updateBrand(BrandDto brandDto);
    public void deleteBrandById(Long id);
}
