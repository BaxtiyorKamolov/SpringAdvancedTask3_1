package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.CategoryTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.CategoryTableDto;
import com.example.springadvancedtask2_2.repository.CategoryTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryTableService {
    @Autowired
    CategoryTableRepository categoryTableRepository;

    public List<CategoryTable> getCategoryTable() {
        return categoryTableRepository.findAll();
    }

    public CategoryTable getCategoryTableById(Integer id) {
        Optional<CategoryTable> optionalCategoryTable = categoryTableRepository.findById(id);
        return optionalCategoryTable.orElse(null);
    }

    public List<CategoryTable> getCategoryTableAllById(List<Integer> ids) {
        return categoryTableRepository.findAllById(ids);
    }

    public ApiResponse addCategoryTable(CategoryTableDto categoryTableDto) {
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setName(categoryTableDto.getName());

        categoryTableRepository.save(categoryTable);
        return new ApiResponse("Qo'shildi", true);
    }

    public ApiResponse editCategoryTable(Integer id, CategoryTableDto categoryTableDto) {
        Optional<CategoryTable> optionalCategoryTable = categoryTableRepository.findById(id);
        if (optionalCategoryTable.isEmpty()) {
            return new ApiResponse("Mavjud emas", false);
        }
        CategoryTable categoryTable = optionalCategoryTable.get();
        categoryTable.setName(categoryTableDto.getName());

        categoryTableRepository.save(categoryTable);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteCategoryTable(Integer id) {
        try {
            categoryTableRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
