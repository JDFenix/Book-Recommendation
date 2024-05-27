package com.example.system_inventory_product.service.book;

import com.example.system_inventory_product.api.BookApi;
import com.example.system_inventory_product.dto.book.BookDetailsDto;
import com.example.system_inventory_product.dto.book.BookDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Book;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.exception.ResourceNotFoundException;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import com.example.system_inventory_product.repository.book.IBookRepository;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookApi bookApi;
    @Autowired
    private IBookRepository iBookRepository;
    @Autowired
    private IAuthorRepository iAuthorRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Transactional
    public BookDetailsDto getDetailsBook(Book book) {
        BookDetailsDto bookDetails = bookApi.getBookDetails(book.getAuthor().getName(), book.getAuthor().getSurname(), book.getName());
        if (bookDetails == null) {
            throw new ResourceNotFoundException("Detalles del libro no encontrados");
        }
        return bookDetails;
    }


    public Book convertToEntityBook(String nameBook, String image, Integer quantity) {
        return new Book();
    }

    @Transactional
    public BookDto getBookById(Long id) {
        Book book = iBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el libro con dicho id: " + id));
        return convertToDto(book);
    }

    @Transactional(readOnly = true)
    public List<BookDto> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = iBookRepository.findAll(pageable);
        return booksPage.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public BookDto convertToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setName(book.getName());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setCategory(book.getCategory());
        bookDto.setImage(book.getImage());
        return bookDto;
    }

    @Transactional
    public void destroy(Long bookId) {
        if (!iBookRepository.existsById(bookId)) {
            throw new ResourceNotFoundException("No se encontró el libro con dicho id: " + bookId);
        }
        iBookRepository.deleteById(bookId);
    }

    @Transactional
    public BookDto update(BookDto bookDto) {
        Book book = iBookRepository.findById(bookDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Libro no encontrado con el id: " + bookDto.getId()));

        book.setName(bookDto.getName());
        book.setImage(bookDto.getImage());

        Author author = iAuthorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con el id: " + bookDto.getAuthor().getId()));
        book.setAuthor(author);

        Category category = iCategoryRepository.findById(bookDto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el id: " + bookDto.getCategory().getId()));
        book.setCategory(category);

         iBookRepository.save(book);
         return convertToDto(book);
    }

    @Transactional
    public BookDto create(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setImage(bookDto.getImage());

        Author author = iAuthorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Autor no encontrado con el id: " + bookDto.getAuthor().getId()));
        book.setAuthor(author);

        Category category = iCategoryRepository.findById(bookDto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con el id: " + bookDto.getCategory().getId()));
        book.setCategory(category);

         iBookRepository.save(book);
         return convertToDto(book);
    }
}
