package com.example.system_inventory_product.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String avatar;

    @Column(unique = true)
    @NotNull(message = "No se puede dejar vacio e{ campo de nombre de usuario")
    private String username;

    @Column(unique = true)
    @Email(message = "correo electronico invalido")
    @NotNull(message = "No se puede dejar vacio el campo de correo electronico")
    private String email;

    @Column
    @NotNull(message = "No se puede dejar vacio el campo de contraseña")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    @NotNull
    private Role role;


    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email=email;
        this.password =password;
        this.role=role;
    }

    public User() {

    }
}
