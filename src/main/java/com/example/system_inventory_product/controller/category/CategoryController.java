package com.example.system_inventory_product.controller.category;

import com.example.system_inventory_product.dto.category.CategoryDto;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.service.category.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/createForm")
    public String showCreateForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authenticatedUser");
        List<CategoryDto> allCategory = categoryService.findAll();
        model.addAttribute("allCategory", allCategory);
        model.addAttribute("user", user);
        model.addAttribute("categoryDto", new CategoryDto());
        return "/category/create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return "redirect:/category/createForm";
    }




    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute CategoryDto categoryDto) {
        categoryService.update(id, categoryDto);
        return "redirect:/category/createForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/category/createForm";
    }
}
