package com.example.system_inventory_product.service.author;

import com.example.system_inventory_product.dto.author.AuthorDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private IAuthorRepository iAuthorRepository;

    public Author create(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        return iAuthorRepository.save(author);
    }

    public Author update(Long id, AuthorDto authorDto) {
        Author existingAuthor = getById(authorDto.getId());
        existingAuthor.setName(authorDto.getName());
        existingAuthor.setSurname(authorDto.getSurname());
        return iAuthorRepository.save(existingAuthor);
    }

    public void delete(Long id) {
        iAuthorRepository.deleteById(id);
    }

    public List<Author> findAll() {
        return iAuthorRepository.findAll();
    }

    public Author getById(Long id) {
        return iAuthorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    private Author convertToEntity(AuthorDto authorDto) {
        return new Author(authorDto.getId(), authorDto.getName(), authorDto.getSurname());
    }
}
