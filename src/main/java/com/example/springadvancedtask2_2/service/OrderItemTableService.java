package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.OrderItemTable;
import com.example.springadvancedtask2_2.entity.OrderTable;
import com.example.springadvancedtask2_2.entity.ProductTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.OrderItemTableDto;
import com.example.springadvancedtask2_2.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemTableService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductTableService productTableService;

    @Autowired
    OrderTableService orderTableService;

    public List<OrderItemTable> getOrderItemTable() {
        return orderItemRepository.findAll();
    }

    public OrderItemTable getOrderItemTableById(Integer id) {
        Optional<OrderItemTable> optionalOrderItemTable = orderItemRepository.findById(id);
        return optionalOrderItemTable.orElse(null);
    }

    public ApiResponse addOrderItemTable(OrderItemTableDto orderItemTableDto) {
        OrderItemTable orderItemTable = new OrderItemTable();
        orderItemTable.setQuantity(orderItemTableDto.getQuantity());
        orderItemTable.setPrice(orderItemTableDto.getPrice());

        List<OrderTable> orderTableAllById = orderTableService.getOrderTableAllById(
                orderItemTableDto.getOrderTables());
        orderItemTable.setOrderTables(orderTableAllById);

        List<ProductTable> productTableAllById = productTableService.getProductTableAllById(
                orderItemTableDto.getProductTables());
        orderItemTable.setProductTables(productTableAllById);

        orderItemRepository.save(orderItemTable);
        return new ApiResponse("Qoshildi", true);
    }

    public ApiResponse editOrderItemTable(Integer id, OrderItemTableDto orderItemTableDto) {
        Optional<OrderItemTable> optionalOrderItemTable = orderItemRepository.findById(id);
        if (optionalOrderItemTable.isEmpty()) {
            return new ApiResponse("MAVJUD EMAS", false);
        }
        OrderItemTable orderItemTable = optionalOrderItemTable.get();
        orderItemTable.setQuantity(orderItemTableDto.getQuantity());
        orderItemTable.setPrice(orderItemTableDto.getPrice());

        List<OrderTable> orderTableAllById = orderTableService.getOrderTableAllById(
                orderItemTableDto.getOrderTables());
        orderItemTable.setOrderTables(orderTableAllById);

        List<ProductTable> productTableAllById = productTableService.getProductTableAllById(
                orderItemTableDto.getProductTables());

        orderItemRepository.save(orderItemTable);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteOrderItemTable(Integer id) {
        try {
            orderItemRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mavjud emas", false);
        }
    }
}
