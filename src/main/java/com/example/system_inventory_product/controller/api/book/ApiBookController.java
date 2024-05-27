package com.example.system_inventory_product.controller.api.book;

import com.example.system_inventory_product.controller.api.StandardResponse;
import com.example.system_inventory_product.dto.book.BookDto;
import com.example.system_inventory_product.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class ApiBookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<BookDto> bookDtoList = bookService.getAllBooks(page, size);
        return ResponseEntity.ok(bookDtoList);
    }

    @GetMapping("/BookById/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        BookDto updatedBook = bookService.update(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
