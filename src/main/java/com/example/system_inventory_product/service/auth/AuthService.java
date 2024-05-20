package com.example.system_inventory_product.service.auth;

import com.example.system_inventory_product.api.AvatarApi;
import com.example.system_inventory_product.dto.auth.LoginFormDto;
import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.entity.user.Role;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private AvatarApi avatarApi;

    public boolean login(LoginFormDto loginFormDto) {
        User user = iUserRepository.findUserByEmail(loginFormDto.getEmail());
        if (user != null && user.getPassword().equals(loginFormDto.getPassword())) {
            return true;
        }
        return false;
    }


    public User createUser(UserDto userDto) {
        try {
            String avatar = avatarApi.getAvatarApi(userDto.getUsername());
            User user = convertToEntity(userDto);
            user.setAvatar(avatar);
            System.out.println("avatar" + avatar );
            iUserRepository.save(user);
            return user;
        } catch (Exception e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
            throw new RuntimeException("Error al crear el usuario", e);

        }
    }


    public User convertToEntity(UserDto userDto){

        return  new User(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                Role.reader);
    }
}
