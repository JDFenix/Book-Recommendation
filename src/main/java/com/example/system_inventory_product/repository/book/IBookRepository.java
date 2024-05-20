package com.example.system_inventory_product.repository.book;

import com.example.system_inventory_product.entity.library.Author;
import com.example.system_inventory_product.entity.library.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long> {
List<Book> findAllByAuthor(Author author);
Book findBookById(Long id);
}
