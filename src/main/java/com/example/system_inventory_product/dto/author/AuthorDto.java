package com.example.system_inventory_product.dto.author;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDto
{
    private Long id;
    private String name;
    private String surname;
}
