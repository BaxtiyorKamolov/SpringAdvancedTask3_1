package com.example.springadvancedtask2_2.service;

import com.example.springadvancedtask2_2.entity.UserTable;
import com.example.springadvancedtask2_2.payload.ApiResponse;
import com.example.springadvancedtask2_2.payload.UserTableDto;
import com.example.springadvancedtask2_2.repository.UserTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTableService {
    @Autowired
    UserTableRepository userTableRepository;

    public List<UserTable> getUserTable() {
        return userTableRepository.findAll();
    }

    public UserTable getUserTableById(Integer id) {
        Optional<UserTable> userTableOptional = userTableRepository.findById(id);
        return userTableOptional.orElse(null);
    }

    public List<UserTable> getUserTableAllById(List<Integer> userId) {
        return userTableRepository.findAllById(userId);
    }

    public ApiResponse addUserTable(UserTableDto userTableDto) {
        boolean existsByPhoneNumber = userTableRepository.existsByPhoneNumber(userTableDto.getPhoneNumber());
        if (existsByPhoneNumber) {

            return new ApiResponse("Bunday phoneNumber mavjud", false);
        }
        UserTable userTable = new UserTable();
        userTable.setName(userTableDto.getName());
        userTable.setEmail(userTableDto.getEmail());
        userTable.setPassword(userTableDto.getPassword());
        userTable.setAddress(userTableDto.getAddress());
        userTable.setPhoneNumber(userTableDto.getPhoneNumber());

        userTableRepository.save(userTable);
        return new ApiResponse("Saqlandi mijoz", true);
    }

    public ApiResponse editUserTable(Integer id, UserTableDto userTableDto) {
        boolean existsByPhoneNumberAndUserIdNot = userTableRepository.existsByPhoneNumberAndUserIdNot(
                userTableDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndUserIdNot) {
            return new ApiResponse("Bunday phoneNumber mavjud", false);
        }
        Optional<UserTable> optionalUserTable = userTableRepository.findById(id);
        if (optionalUserTable.isEmpty()) {
            return new ApiResponse("Bunday phoneNumber mavjud emas", false);
        }
        UserTable userTable = optionalUserTable.get();
        userTable.setName(userTableDto.getName());
        userTable.setEmail(userTableDto.getEmail());
        userTable.setPassword(userTableDto.getPassword());
        userTable.setAddress(userTableDto.getAddress());
        userTable.setPhoneNumber(userTableDto.getPhoneNumber());

        userTableRepository.save(userTable);
        return new ApiResponse("Mijoz tahrirlandi", true);
    }

    public ApiResponse deleteUserTable(Integer id) {
        try {
            userTableRepository.findById(id);
            return new ApiResponse("Mijoz o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Mijoz topilmadi", true);
        }
    }
}
