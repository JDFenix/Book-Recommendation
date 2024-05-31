package com.example.system_inventory_product.controller.api.book;

import com.example.system_inventory_product.controller.api.StandardResponse;
import com.example.system_inventory_product.dto.book.BookDto;
import com.example.system_inventory_product.service.book.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class ApiBookController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("hasAuthority('READ_ALL_OBJECTS')")
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<BookDto> bookDtoList = bookService.getAllBooks(page, size);
        return ResponseEntity.ok(bookDtoList);
    }

    @PreAuthorize("hasAuthority('READ_ONE_OBJECT')")
    @GetMapping("/BookById/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_OBJECT')")
    @PostMapping("/create")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto createdBook = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_OBJECT')")
    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        BookDto updatedBook = bookService.update(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @PreAuthorize("hasAuthority('DELETE_ONE_OBJECT')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.destroy(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleException(Exception ex, HttpServletRequest request) {
        Map<String,Object> apiError = new HashMap<>();
        apiError.put("Message",ex.getLocalizedMessage());
        apiError.put("timestamp",new Date().toString());
        apiError.put("url",request.getRequestURL());
        apiError.put("httpMethod",request.getMethod());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof AccessDeniedException){
            status = HttpStatus.FORBIDDEN;
        }
        return ResponseEntity.status(status).body(apiError);
    }
}
