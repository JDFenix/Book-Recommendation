package com.example.system_inventory_product.service.author;

import com.example.system_inventory_product.dto.author.AuthorDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.exception.ResourceNotFoundException;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        try {
            Author author = convertToEntity(authorDto);
            Author savedAuthor = iAuthorRepository.save(author);
            return convertToDto(savedAuthor);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al crear el autor: " + ex.getMessage(), ex);
        }
    }

    @Transactional
    public AuthorDto update(Long id, AuthorDto authorDto) {
        try {
            Author existingAuthor = getByIdEntity(id);
            existingAuthor.setName(authorDto.getName());
            existingAuthor.setSurname(authorDto.getSurname());
            Author updatedAuthor = iAuthorRepository.save(existingAuthor);
            return convertToDto(updatedAuthor);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al actualizar el autor: " + ex.getMessage(), ex);
        }
    }

    @Transactional
    public void delete(Long id) {
        try {
            Author existingAuthor = getByIdEntity(id);
            iAuthorRepository.delete(existingAuthor);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al eliminar el autor: " + ex.getMessage(), ex);
        }
    }

    @Transactional(readOnly = true)
    public List<AuthorDto> findAll(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            return iAuthorRepository.findAll(pageable).stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al recuperar los autores: " + ex.getMessage(), ex);
        }
    }

    @Transactional(readOnly = true)
    public AuthorDto getById(Long id) {
        try {
            Author author = getByIdEntity(id);
            return convertToDto(author);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Error al recuperar el autor: " + ex.getMessage(), ex);
        }
    }

    private Author getByIdEntity(Long id) {
        return iAuthorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con el ID: " + id));
    }

    private Author convertToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getSurname());
    }

    private AuthorDto convertToDto(Author author) {
        return new AuthorDto(author.getId(), author.getName(), author.getSurname());
    }
}
