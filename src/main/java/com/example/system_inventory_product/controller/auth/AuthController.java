package com.example.system_inventory_product.controller.auth;

import com.example.system_inventory_product.api.AvatarApi;
import com.example.system_inventory_product.dto.auth.LoginFormDto;
import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.service.auth.AuthService;
import com.example.system_inventory_product.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;



    @GetMapping("/error")
    public String showError(Model model) {
        return "/error";
    }


    @GetMapping("/loginC")
    public String showFor(Model model) {
        model.addAttribute("loginFormDto", new LoginFormDto());
        return "/auth/loginC";
    }
    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        model.addAttribute("userDto",new UserDto());
        return "auth/register";
    }





    @PostMapping("/loginRequest")
    public String login(@ModelAttribute("loginFormDto") LoginFormDto loginFormDto, Model model, HttpServletRequest request) {
        try {
            boolean isAuthenticated = authService.login(loginFormDto);
            User user = userService.getUserByEmail(loginFormDto.getEmail());
            String url = "redirect:/book/showAll";
            if (isAuthenticated && user!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("authenticatedUser", user);
                return url;
            } else {
                model.addAttribute("error", "Credenciales inválidas");
                return "redirect:/error";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error durante la autenticación" + e.getMessage());
            return "redirect:/error";
        }
    }





    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        try {
            User user = authService.createUser(userDto);
            return "redirect:/auth/loginC";
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/auth/loginC";
    }
}
