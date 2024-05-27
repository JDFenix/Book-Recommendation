package com.example.system_inventory_product.service.user;

import com.example.system_inventory_product.api.AvatarApi;
import com.example.system_inventory_product.controller.api.StandardResponse;
import com.example.system_inventory_product.dto.user.UserDto;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.exception.ResourceNotFoundException;
import com.example.system_inventory_product.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private AvatarApi avatarApi;

    @Transactional(readOnly = true)
    public UserDto getUserByEmail(String email) {
        Optional<User> optionalUser = iUserRepository.findUserByEmail(email);
        return optionalUser.map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el correo: " + email));
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = iUserRepository.findAll(pageable);
        return userPage.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = iUserRepository.findById(id);
        return optionalUser.map(this::convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        String avatar = avatarApi.getAvatarApi(userDto.getUsername());
        if (avatar == null) {
            throw new RuntimeException("Error al obtener el avatar");
        }

        User user = convertToEntity(userDto);
        user.setAvatar(avatar);

        User savedUser = iUserRepository.save(user);
        return convertToDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(Long id, UserDto updatedUserDto) {
        User existingUser = iUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el ID: " + id));

        existingUser.setUsername(updatedUserDto.getUsername());
        existingUser.setEmail(updatedUserDto.getEmail());
        existingUser.setPassword(updatedUserDto.getPassword());
        existingUser.setRole(updatedUserDto.getRole());

        User savedUser = iUserRepository.save(existingUser);
        return convertToDto(savedUser);
    }

    @Transactional
    public void deleteUserById(Long userId) {
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("El usuario con ID: " + userId + " no fue encontrado"));
        iUserRepository.delete(user);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getAvatar(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }

    private User convertToEntity(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword(), userDto.getRole());
    }
}
