package com.example.system_inventory_product.dto.book;

import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Category;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class BookDto {
    private Long id;
    private String name;
    private String image;
    private Author author;
    private Category category;

}
