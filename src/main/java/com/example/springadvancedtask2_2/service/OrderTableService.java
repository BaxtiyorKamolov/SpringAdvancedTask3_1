package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.OrderTable;
import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.OrderTableDto;
import com.example.springadvancedtask2_2.repository.OrderTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTableService {
    @Autowired
    OrderTableRepository orderTableRepository;

    @Autowired
    UserTableService userTableService;

    public List<OrderTable> getOrderTable() {
        return orderTableRepository.findAll();
    }

    public OrderTable getOrderTableById(Integer id) {
        Optional<OrderTable> optionalOrderTable = orderTableRepository.findById(id);
        return optionalOrderTable.orElse(null);
    }

    public List<OrderTable> getOrderTableAllById(List<Integer> ids) {
        return orderTableRepository.findAllById(ids);
    }

    public ApiResponse addOrderTable(OrderTableDto orderTableDto) {
        OrderTable orderTable = new OrderTable();
        orderTable.setOrderDate(orderTableDto.getOrderDate());
        orderTable.setTotalAmount(orderTableDto.getTotalAmount());

        List<UserTable> userTableAllById = userTableService.getUserTableAllById(
                orderTableDto.getUserTablesIds());
        orderTable.setUserTables(userTableAllById);

        orderTableRepository.save(orderTable);
        return new ApiResponse("Qoshildi", true);
    }

    public ApiResponse editOrderTable(Integer id, OrderTableDto orderTableDto) {
        Optional<OrderTable> optionalOrderTable = orderTableRepository.findById(id);
        if (optionalOrderTable.isEmpty()) {
            return new ApiResponse("Bunday order mavjud emas", false);
        }
        OrderTable orderTable = optionalOrderTable.get();
        orderTable.setOrderDate(orderTableDto.getOrderDate());
        orderTable.setTotalAmount(orderTableDto.getTotalAmount());

        List<UserTable> userTableAllById = userTableService.getUserTableAllById(
                orderTableDto.getUserTablesIds());
        orderTable.setUserTables(userTableAllById);

        orderTableRepository.save(orderTable);
        return new ApiResponse("Tahrirlandi", true);
    }

    public ApiResponse deleteOrderTable(Integer id) {
        try {
            orderTableRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Topilmadi", false);
        }
    }
}
