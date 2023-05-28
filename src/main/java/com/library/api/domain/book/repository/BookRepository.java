package com.library.api.domain.book.repository;

import com.library.api.domain.book.repository.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    @Query("SELECT b from Book b LEFT JOIN FETCH b.authors WHERE b IN ?1")
    List<Book> findByIdListFetchAuthors(List<Book> books);

    List<Book> findAllByNameContaining(String name, Pageable pageable);

//    Book findByBookId(Long id);
}
