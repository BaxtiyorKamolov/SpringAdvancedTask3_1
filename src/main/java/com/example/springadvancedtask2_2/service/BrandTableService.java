package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.BrandTable;
import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.BrandTableDto;
import com.example.springadvancedtask2_2.payload.UserTableDto;
import com.example.springadvancedtask2_2.repository.BrandTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandTableService {
    @Autowired
    BrandTableRepository brandTableRepository;

    public List<BrandTable> getBrandTable() {
        return brandTableRepository.findAll();
    }

    public BrandTable getBrandTableById(Integer id) {
        Optional<BrandTable> optionalBrandTable = brandTableRepository.findById(id);
        return optionalBrandTable.orElse(null);
    }

    public List<BrandTable> getBrandTableAllById(List<Integer> ids) {
        return brandTableRepository.findAllById(ids);
    }

    public ApiResponse addBrandTable(BrandTableDto brandTableDto) {
        BrandTable brandTable = new BrandTable();
        brandTable.setName(brandTableDto.getName());

        brandTableRepository.save(brandTable);
        return new ApiResponse("Qo'shildi", true);
    }

    public ApiResponse editBrandTable(Integer id, BrandTableDto brandTableDto) {
        Optional<BrandTable> optionalBrandTable = brandTableRepository.findById(id);
        if (optionalBrandTable.isEmpty()) {
            return new ApiResponse("Mavjud emas", false);
        }
        BrandTable brandTable = optionalBrandTable.get();
        brandTable.setName(brandTableDto.getName());

        brandTableRepository.save(brandTable);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteBrandTable(Integer id) {
        try {
            brandTableRepository.findById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
