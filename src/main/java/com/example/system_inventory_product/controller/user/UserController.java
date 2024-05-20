package com.example.system_inventory_product.controller.user;


import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @ModelAttribute("userController")
    public boolean hasRole(@NotBlank(message = "Rol es requerido") String role, HttpServletRequest request) { // Add parameter name "role"
        User user = (User) request.getSession().getAttribute("authenticatedUser");
        return user != null && user.getRole().name().equals(role);
    }



}
