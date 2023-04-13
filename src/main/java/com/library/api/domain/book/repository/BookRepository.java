package com.library.api.domain.book.repository;

import com.library.api.domain.book.repository.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

//    Book findByBookId(Long id);
}
