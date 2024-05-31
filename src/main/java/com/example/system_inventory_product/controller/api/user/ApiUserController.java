package com.example.system_inventory_product.controller.api.user;

import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('READ_ALL_OBJECTS')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "3") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Las variables ingresadas no pueden ser negativas y deben ser mayores a 0");
        }
        List<UserDto> userDtoList = userService.getAllUsers(page, size);
        return ResponseEntity.ok(userDtoList);
    }

    @PreAuthorize("hasAuthority('READ_ONE_OBJECT')")
    @GetMapping("/userById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasAuthority('READ_ONE_OBJECT')")
    @GetMapping("/userByEmail")
    public ResponseEntity<UserDto> getUserByEmail(@RequestParam String email) {
        UserDto userDto = userService.getUserByEmail(email);
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_OBJECT')")
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_OBJECT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasAuthority('DELETE_ONE_OBJECT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
