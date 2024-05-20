package com.example.system_inventory_product.entity.library;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity(name = "books")
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;


    @Column
    @NotNull
    private  String image;


    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false)
    private   Author author;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private   Category category;



}
