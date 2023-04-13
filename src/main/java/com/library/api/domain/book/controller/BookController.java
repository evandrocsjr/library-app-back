package com.library.api.domain.book.controller;

import com.library.api.domain.book.controller.v1.dto.BookWebDTO;
import com.library.api.domain.book.service.BookService;
import com.library.api.domain.book.service.dto.BookDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/books")
@CrossOrigin
@RequiredArgsConstructor
@Api(tags = "Books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ApiOperation("Cadastra um Livro.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Livro cadastrado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public BookWebDTO create(@RequestBody BookWebDTO bookDTO) {
        ModelMapper mapper = new ModelMapper();
        BookDTO newBook = bookService.createBook(mapper.map(bookDTO, BookDTO.class));
        return mapper.map(newBook, BookWebDTO.class);
    }

    @GetMapping
    @ApiOperation("Recupera os Livros da licença.")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAll(){
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/{id}")
    @ApiOperation("Recupera um Livro através do id.")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getById(@PathVariable Long id){
        return bookService.getBookById(id);
    }
}
