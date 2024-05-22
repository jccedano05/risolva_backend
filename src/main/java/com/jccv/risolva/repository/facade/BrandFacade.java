package com.jccv.risolva.repository.facade;

import com.jccv.risolva.dto.BrandDto;
import com.jccv.risolva.dto.mapper.BrandMapper;
import com.jccv.risolva.exception.ResourceNotFoundException;
import com.jccv.risolva.model.Brand;
import com.jccv.risolva.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BrandFacade {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;


    public BrandDto findBrandByID (Long id){
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No brand founded with Id: " + id));
        return brandMapper.convertModelToDto(brand);
    }

    public List<BrandDto> findAllBrands(){
        List<Brand> paymentsResident = brandRepository.findAll();
        return paymentsResident.stream().map(brand -> brandMapper.convertModelToDto(brand)).collect(Collectors.toList());
    }

    public BrandDto createBrand(BrandDto brandDto) {
        Brand brand = brandMapper.convertDtoModel(brandDto);
        brand = brandRepository.save(brand);
        return brandMapper.convertModelToDto(brand);
    }

    public BrandDto updateBrand(BrandDto brandDto) {

        if (brandDto.getId() != null && brandRepository.existsById(brandDto.getId())) {
            Brand brand = brandRepository.save(brandMapper.convertDtoModel(brandDto));
            return brandMapper.convertModelToDto(brand);
        } else {
            throw new ResourceNotFoundException("No brand founded with Id: " + brandDto.getId());
        }
    }

    public void deleteBrandById(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No brand founded with Id: " + id);
        }
    }


}
