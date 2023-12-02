package com.library.api.domain.book.controller.v1;

import com.library.api.domain.book.controller.v1.dto.BookWebDTO;
import com.library.api.domain.book.service.BookService;
import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/books")
@RequiredArgsConstructor
@Tag(name = "Books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Cadastra um Livro.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro cadastrado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public BookWebDTO create(@RequestBody BookWebDTO bookDTO) {
        ModelMapper mapper = new ModelMapper();
        BookDTO newBook = bookService.createBook(mapper.map(bookDTO, BookDTO.class));
        return mapper.map(newBook, BookWebDTO.class);
    }

    @GetMapping("/getAllByLicense")
    @Operation(summary = "Recupera os Livros da licença.")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAllByLicense() {
        return bookService.getAllBooksByLicense();
    }

    @GetMapping
    @Operation(summary = "Recupera os Livros da Licença(resultado paginado).")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> getAll(@RequestParam(value = "name", required = false) String bookName,
                                @RequestParam("page") int page) {
        return bookService.getAllBooksFiltered(bookName, page - 1);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Recupera um Livro através do id.")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getById(@PathVariable Long id) throws NotFoundException {
        return bookService.getBookById(id);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Exclui um Livro através do id.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PutMapping
    @Operation(summary = "Atualiza um Livro pelo id.")
    @ResponseStatus
    public void update() {

    }
}