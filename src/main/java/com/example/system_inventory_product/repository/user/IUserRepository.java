package com.example.system_inventory_product.repository.user;

import com.example.system_inventory_product.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {

    User findUserByEmail(String email);
}
