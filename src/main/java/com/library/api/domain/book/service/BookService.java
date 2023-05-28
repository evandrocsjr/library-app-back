package com.library.api.domain.book.service;

import com.library.api.domain.DefaultService;
import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService extends DefaultService {

    private final BookRepository bookRepository;

    public BookDTO createBook(BookDTO newBook) {
        ModelMapper mapper = new ModelMapper();
        newBook.setCreatedAt(new Date());
        Book bookDb = bookRepository.save(mapper.map(newBook, Book.class));
        return mapper.map(bookDb, BookDTO.class);
    }

    @Transactional
    public List<BookDTO> getAllBooksByLicense() {
        List<Book> bookList = bookRepository.findAll();
        bookRepository.findByIdListFetchAuthors(bookList);
        ModelMapper mapper = new ModelMapper();
        return bookList.stream().map(b -> mapper.map(b, BookDTO.class)).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksFiltered(String bookName, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        List<Book> list;
        ModelMapper mapper = new ModelMapper();
        list = bookRepository.findAllByNameContaining(bookName, pageable);

        return list.stream().map(b -> mapper.map(b, BookDTO.class)).collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) {
        return new ModelMapper().map(bookRepository.findById(id), BookDTO.class);
    }
}
