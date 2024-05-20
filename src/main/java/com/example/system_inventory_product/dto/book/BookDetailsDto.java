package com.example.system_inventory_product.dto.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsDto {
    private String description;
    private String publisher;
    private String publisherDate;
    private String currencyCode;
    private double retailPrice;
    private String buyLink;
}
