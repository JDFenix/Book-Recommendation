package com.example.system_inventory_product.initializer.book;

import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Book;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import com.example.system_inventory_product.repository.book.IBookRepository;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BookDataInitializer implements CommandLineRunner {

    @Autowired
    private IBookRepository bookRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private IAuthorRepository authorRepository;

    public BookDataInitializer(IBookRepository bookRepository, ICategoryRepository categoryRepository, IAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;

    }


    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0 && authorRepository.count() == 0 && categoryRepository.count() == 0) {

            Category category = new Category();
            category.setName("Ficción");
            categoryRepository.save(category);

            Author author = new Author();
            author.setName("Dan");
            author.setSurname("Brown");

            Book book = new Book();
            book.setName("La conspiración");
            book.setCategory(category);
            book.setImage("https://th.bing.com/th/id/OIP.XxmAbtarIb83rkqnKfFvywHaLT?rs=1&pid=ImgDetMain");
            book.setAuthor(author);

            authorRepository.save(author);
            bookRepository.save(book);


        }
    }
}
