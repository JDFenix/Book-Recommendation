package com.example.system_inventory_product.controller.author;

import com.example.system_inventory_product.dto.author.AuthorDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.service.author.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/registerForm")
    public String showForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authenticatedUser");
        List<Author> authorList = authorService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("authorDto", new AuthorDto());
        model.addAttribute("authorList", authorList);
        model.addAttribute("author", new Author());
        return "author/create";
    }


    @PostMapping("/register")
    public String create(@ModelAttribute("authorDto") AuthorDto authorDto) {
        authorService.create(authorDto);
        return "redirect:/author/registerForm";
    }


    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("authorDto") AuthorDto authorDto) {
        authorService.update(id, authorDto);
        return "redirect:/author/registerForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/author/registerForm";
    }
}
