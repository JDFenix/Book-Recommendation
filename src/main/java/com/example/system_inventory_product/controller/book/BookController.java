package com.example.system_inventory_product.controller.book;


import com.example.system_inventory_product.dto.book.BookDetailsDto;
import com.example.system_inventory_product.dto.book.BookDto;
import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Category;
import com.example.system_inventory_product.entity.user.User;
import com.example.system_inventory_product.repository.author.IAuthorRepository;
import com.example.system_inventory_product.repository.category.ICategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.example.system_inventory_product.entity.library.Book;
import com.example.system_inventory_product.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookService bookService;

    @Autowired
    private IAuthorRepository iAuthorRepository;

    @Autowired
    private ICategoryRepository iCategoryRepository;


//    @GetMapping("/showAll")
//    public String homeBook(Model model, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("authenticatedUser");
//        List<Book> books = bookService.getAllBooks();
//        model.addAttribute("user", user);
//        model.addAttribute("books", books);
//        return "book/show";
//    }


//    @GetMapping("/details/{bookId}")
//    public String showBookDetails(@PathVariable Long bookId, HttpServletRequest request, Model model) {
//
//        try {
//            User user = (User) request.getSession().getAttribute("authenticatedUser");
//            Book book = bookService.getBookById(bookId);
//            BookDetailsDto bookDetails = bookService.getDetailsBook(book);
//
//            model.addAttribute("book", book);
//            model.addAttribute("bookDetails", bookDetails);
//            model.addAttribute("user", user);
//            return "book/details";
//        } catch (InternalError e) {
//            model.addAttribute("error", new InternalError(e));
//            return "error";
//        }
//    }

    @GetMapping("/delete/{bookId}")
    public String destroy(@PathVariable Long bookId) {
        try {
            bookService.destroy(bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/book/showAll";
    }

//    @GetMapping("/updateForm/{bookId}")
//    public String showForm(@PathVariable Long bookId, Model model, HttpServletRequest request) {
//        User user = (User) request.getSession().getAttribute("authenticatedUser");
//        Book book = bookService.getBookById(bookId);
//        List<Author> authors = iAuthorRepository.findAll();
//        List<Category> categories = iCategoryRepository.findAll();
//
//        BookDto bookDto = new BookDto();
//        bookDto.setId(book.getId());
//        bookDto.setName(book.getName());
//        bookDto.setImage(book.getImage());
//        bookDto.setAuthor(book.getAuthor());
//        bookDto.setCategory(book.getCategory());
//
//        model.addAttribute("book", book);
//        model.addAttribute("authors", authors);
//        model.addAttribute("categories", categories);
//        model.addAttribute("bookDto", bookDto);
//        model.addAttribute("user", user);
//        return "book/update";
//    }

    @PostMapping("/update")
    public String update(@ModelAttribute("bookDto") BookDto bookDto) {
        bookService.update(bookDto);
        return "redirect:/book/details/" + bookDto.getId();
    }

    @GetMapping("/createForm")
    public String showCreateForm(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("authenticatedUser");
        List<Author> authors = iAuthorRepository.findAll();
        List<Category> categories = iCategoryRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);
        model.addAttribute("bookDto", new BookDto());
        return "book/create";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto bookDto) {
        bookService.create(bookDto);
        return "redirect:/book/showAll";
    }


//    @GetMapping("/search")
//    public String searchBook(AuthorDto authorDto, Model model) {
//        try {
//            List<Book> books = bookService.getBookByAuthor(authorDto);
//            model.addAttribute("books", books);
//            return "";
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException(e);
//        }
//    }


}
