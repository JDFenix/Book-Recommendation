package com.example.system_inventory_product.repository.author;

import com.example.system_inventory_product.entity.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthorRepository extends JpaRepository<Author,Long> {
}
