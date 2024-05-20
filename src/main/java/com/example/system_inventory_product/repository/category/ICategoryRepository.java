package com.example.system_inventory_product.repository.category;

import com.example.system_inventory_product.entity.library.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Category findAllByName(String nameCategory);
}
