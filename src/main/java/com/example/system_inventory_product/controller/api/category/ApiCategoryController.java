package com.example.system_inventory_product.controller.api.category;

import com.example.system_inventory_product.dto.category.CategoryDto;
import com.example.system_inventory_product.service.category.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/category")
public class ApiCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAuthority('READ_ALL_OBJECTS')")
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<?>> getAllCategories(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Las variables ingresadas no pueden ser negativas y deben ser mayores a 0");
        }
        List<?> categoryList = categoryService.getAllCategories(page, size);
        return ResponseEntity.ok(categoryList);
    }

    @PreAuthorize("hasAuthority('READ_ONE_OBJECT')")
    @GetMapping("/categoryById/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.findById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_OBJECT')")
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.create(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_OBJECT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.update(id, categoryDto);
        return ResponseEntity.ok(updatedCategory);
    }

    @PreAuthorize("hasAuthority('DELETE_ONE_OBJECT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
