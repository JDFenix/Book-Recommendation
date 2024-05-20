package com.example.system_inventory_product.service.book;

import com.example.system_inventory_product.api.BookApi;
import com.example.system_inventory_product.dto.author.AuthorDto;
import com.example.system_inventory_product.dto.book.BookDetailsDto;
import com.example.system_inventory_product.dto.book.BookDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Book;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import com.example.system_inventory_product.repository.book.IBookRepository;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Book> getAllBooks() {
        return iBookRepository.findAll();
    }

    public BookDetailsDto getDetailsBook(Book book) {
        BookDetailsDto bookDetails = bookApi.getBookDetails(book.getAuthor().getName(), book.getAuthor().getSurname(), book.getName());
        if (bookDetails == null) {
            throw new InternalError("Book details not found.");
        }
        return bookDetails;
    }

    public Book convertToEntityBook(String nameBook, String image, Integer quantity) {
        return new Book();
    }

    public Book getBookById(Long id){
        return iBookRepository.findBookById(id);
    }


    public void destroy(Long bookId) {
        iBookRepository.deleteById(bookId);
    }

    public Book update(BookDto bookDto) {
        Book book = getBookById(bookDto.getId());
        if (book != null) {
            book.setName(bookDto.getName());
            book.setImage(bookDto.getImage());

            Author author = iAuthorRepository.findById(bookDto.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            book.setAuthor(author);

            Category category = iCategoryRepository.findById(bookDto.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            book.setCategory(category);

            iBookRepository.save(book);
            return book;
        } else {
            throw new RuntimeException("Book not found.");
        }
    }


    public void create(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setImage(bookDto.getImage());
        Author author = iAuthorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        book.setAuthor(author);
        Category category = iCategoryRepository.findById(bookDto.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(category);
        iBookRepository.save(book);
    }
}

