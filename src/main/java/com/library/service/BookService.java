package com.library.service;

import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDTO> getAllBooks(){
        List<Book> bookList = bookRepository.findAll();
        ModelMapper mapper = new ModelMapper();
        return bookList.stream().map(b -> mapper.map(b, BookDTO.class)).collect(Collectors.toList());
    }

}
