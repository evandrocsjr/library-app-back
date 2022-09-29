package com.library.controller;

import com.library.dto.BookDTO;
import com.library.service.BookService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v2/books")
@CrossOrigin
@RequiredArgsConstructor
@Api(tags = "Books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAll(){
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
}
