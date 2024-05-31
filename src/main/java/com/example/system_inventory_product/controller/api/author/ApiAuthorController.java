package com.example.system_inventory_product.controller.api.author;

import com.example.system_inventory_product.dto.author.AuthorDto;
import com.example.system_inventory_product.service.author.AuthorService;
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
@RequestMapping("/api/author")
public class ApiAuthorController {

    @Autowired
    private AuthorService authorService;

    @PreAuthorize("hasAuthority('SAVE_ONE_OBJECT')")
    @PostMapping("/create")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto createdAuthor = authorService.create(authorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_OBJECT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        AuthorDto updatedAuthor = authorService.update(id, authorDto);
        return ResponseEntity.ok(updatedAuthor);
    }

    @PreAuthorize("hasAuthority('DELETE_ONE_OBJECT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PreAuthorize("hasAuthority('READ_ALL_OBJECTS')")
    @GetMapping("/getAllAuthors")
    public ResponseEntity<List<AuthorDto>> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size) {
        List<AuthorDto> authors = authorService.findAll(page, size);
        return ResponseEntity.ok(authors);
    }

    @PreAuthorize("hasAuthority('READ_ONE_OBJECT')")
    @GetMapping("/authorById/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@PathVariable Long id) {
        AuthorDto author = authorService.getById(id);
        return ResponseEntity.ok(author);
    }
}
