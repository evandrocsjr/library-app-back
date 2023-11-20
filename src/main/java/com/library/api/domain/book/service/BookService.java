package com.library.api.domain.book.service;

import com.library.api.domain.DefaultService;
import com.library.api.domain.book.repository.enums.BookAvailability;
import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.BookRepository;
import com.library.api.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService extends DefaultService {

    private static final String BOOK_NOT_EXISTS = "Livro não encontrado.";

    private final BookRepository bookRepository;

    public BookDTO createBook(BookDTO newBook) {
        ModelMapper mapper = new ModelMapper();
        newBook.setCreatedAt(new Date());
        newBook.setBookAvailability(BookAvailability.AVAILABLE);
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

    @Transactional
    public List<BookDTO> getAllBooksFiltered(String bookName, int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        List<Book> list;
        ModelMapper mapper = new ModelMapper();
        if(bookName != null && !bookName.isBlank()) {
            list = bookRepository.findAllByNameContaining(bookName, pageable);
        } else {
            list = bookRepository.findAll(pageable).stream().toList();
        }

        return list.stream().map(b -> mapper.map(b, BookDTO.class)).collect(Collectors.toList());
    }

    public BookDTO getBookById(Long id) throws NotFoundException {
        Book bookDb = bookRepository.findByIdFetchAuthors(id);

        if(bookDb == null) {
            throw new NotFoundException(BOOK_NOT_EXISTS);
        }
        return new ModelMapper().map(bookRepository.findByIdFetchAuthors(id), BookDTO.class);
    }

    public void deleteBookById(Long id) {
        Optional<Book> bookDb = bookRepository.findById(id);
        bookRepository.deleteById(bookDb.get().getId());
    }
}
