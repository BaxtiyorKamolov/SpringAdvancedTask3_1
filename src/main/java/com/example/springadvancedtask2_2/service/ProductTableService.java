package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.BrandTable;
import com.example.springadvancedtask2_2.entity.CategoryTable;
import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.ProductTableDto;
import com.example.springadvancedtask2_2.repository.ProductTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTableService {
    @Autowired
    ProductTableRepository productTableRepository;

    @Autowired
    BrandTableService brandTableService;

    @Autowired
    CategoryTableService categoryTableService;

    public List<ProductTable> getProductTable() {
        return productTableRepository.findAll();
    }

    public ProductTable getProductTableById(Integer id) {
        Optional<ProductTable> optionalProductTable = productTableRepository.findById(id);
        return optionalProductTable.orElse(null);
    }

    public List<ProductTable> getProductTableAllById(List<Integer> ids) {
        return productTableRepository.findAllById(ids);
    }

    public ApiResponse addProductTable(ProductTableDto productTableDto) {
        ProductTable productTable = new ProductTable();
        productTable.setName(productTableDto.getName());
        productTable.setDescription(productTableDto.getDescription());
        productTable.setPrice(productTableDto.getPrice());
        productTable.setStockQuantity(productTableDto.getStockQuantity());

        List<BrandTable> brandTableAllById = brandTableService.getBrandTableAllById(
                productTableDto.getBrandTablesId());
        productTable.setBrandTables(brandTableAllById);

        List<CategoryTable> categoryTableAllById = categoryTableService.getCategoryTableAllById(
                productTableDto.getCategoryTablesId());
        productTable.setCategoryTables(categoryTableAllById);

        productTableRepository.save(productTable);
        return new ApiResponse("Qo'shildi", true);
    }

    public ApiResponse editProductTable(Integer id, ProductTableDto productTableDto) {
        Optional<ProductTable> optionalProductTable = productTableRepository.findById(id);
        if (optionalProductTable.isEmpty()) {
            return new ApiResponse("Mavjud emas", false);
        }
        ProductTable productTable = optionalProductTable.get();
        productTable.setName(productTableDto.getName());
        productTable.setDescription(productTableDto.getDescription());
        productTable.setPrice(productTableDto.getPrice());
        productTable.setStockQuantity(productTableDto.getStockQuantity());

        List<BrandTable> brandTableAllById = brandTableService.getBrandTableAllById(
                productTableDto.getBrandTablesId());
        productTable.setBrandTables(brandTableAllById);

        List<CategoryTable> categoryTableAllById = categoryTableService.getCategoryTableAllById(
                productTableDto.getCategoryTablesId());
        productTable.setCategoryTables(categoryTableAllById);

        productTableRepository.save(productTable);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteProductTable(Integer id) {
        try {
            productTableRepository.deleteById(id);
            return new ApiResponse("Ochirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}