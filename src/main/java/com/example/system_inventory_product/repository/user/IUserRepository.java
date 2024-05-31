package com.example.system_inventory_product.repository.user;

import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
