package com.jccv.risolva.service.impl;

import com.jccv.risolva.dto.BrandDto;
import com.jccv.risolva.repository.facade.BrandFacade;
import com.jccv.risolva.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImp implements BrandService {

    @Autowired
    private BrandFacade brandFacade;

    @Override
    public BrandDto findBrandByID(Long id) {
        return brandFacade.findBrandByID(id);
    }

    @Override
    public List<BrandDto> findAllBrands() {
        return brandFacade.findAllBrands();
    }

    @Override
    public BrandDto createBrand(BrandDto brandDto) {
        return brandFacade.createBrand(brandDto);
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto) {
        return brandFacade.updateBrand(brandDto);
    }

    @Override
    public void deleteBrandById(Long id) {
        brandFacade.deleteBrandById(id);

    }
}
