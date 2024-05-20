package com.example.system_inventory_product.service.user;

import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.entity.user.Role;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;



    public  User getUserByEmail(String email){
        return iUserRepository.findUserByEmail(email);
    }







}
