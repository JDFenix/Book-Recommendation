package com.example.system_inventory_product.service.category;

import com.example.system_inventory_product.dto.category.CategoryDto;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    public CategoryDto create(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        category = iCategoryRepository.save(category);
        return convertToDto(category);
    }

    public List<CategoryDto> findAll() {
        return iCategoryRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto findById(Long id) {
        Optional<Category> category = iCategoryRepository.findById(id);
        return category.map(this::convertToDto).orElse(null);
    }

    public CategoryDto update(Long id, CategoryDto categoryDto) {
        if (iCategoryRepository.existsById(id)) {
            Category category = convertToEntity(categoryDto);
            category.setId(id);
            category = iCategoryRepository.save(category);
            return convertToDto(category);
        }
        return null;
    }

    public void delete(Long id) {
        iCategoryRepository.deleteById(id);
    }

    private Category convertToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return category;
    }

    private CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}
