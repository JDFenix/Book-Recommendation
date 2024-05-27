package com.example.system_inventory_product.service.category;

import com.example.system_inventory_product.controller.api.StandardResponse;
import com.example.system_inventory_product.dto.category.CategoryDto;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.exception.ResourceNotFoundException;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Transactional
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = convertToEntity(categoryDto);
        category = iCategoryRepository.save(category);
        return convertToDto(category);
    }

    @Transactional(readOnly = true)
    public List<?> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CategoryDto> categoryList = iCategoryRepository.findAll(pageable)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        if (categoryList.isEmpty()) {
            return List.of(new StandardResponse("No hay categorías registradas", null));
        } else {
            return categoryList;
        }
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(Long id) {
        Optional<Category> category = iCategoryRepository.findById(id);
        return category.map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));
    }

    @Transactional
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        Category category = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));

        category.setName(categoryDto.getName());
        category = iCategoryRepository.save(category);
        return convertToDto(category);
    }

    @Transactional
    public void delete(Long id) {
        Category category = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el ID: " + id));
        iCategoryRepository.delete(category);
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
