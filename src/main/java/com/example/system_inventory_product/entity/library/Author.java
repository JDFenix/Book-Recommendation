package com.example.system_inventory_product.entity.library;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "No se puede dejar vacio el campo de nombre")
    private String name;

    @Column
    @NotNull(message = "No se puede dejar vacio el campo de Apellido")
    private String surname;






}
