package com.library.api.domain.book.service;

import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookDTO createBook(BookDTO newBook) {
        ModelMapper mapper = new ModelMapper();
        newBook.setCreatedAt(new Date());
        Book bookDb = bookRepository.save(mapper.map(newBook, Book.class));
        return mapper.map(bookDb, BookDTO.class);
    }

    public List<BookDTO> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return bookList.stream().map(b -> mapper.map(b, BookDTO.class)).collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return new ModelMapper().map(bookRepository.findById(id), BookDTO.class);
    }
}
