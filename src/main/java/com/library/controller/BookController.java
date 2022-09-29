package com.library.controller;

import com.library.dto.BookDTO;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v2/books")
@CrossOrigin
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAll(){
        return bookService.getAllBooks();
    }
}
