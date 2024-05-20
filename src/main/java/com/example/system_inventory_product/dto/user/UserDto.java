package com.example.system_inventory_product.dto.user;

import com.example.system_inventory_product.entity.user.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class UserDto {
    private String avatar;
    private String username;
    private String email;
    private String password;
    private Role role;


}
