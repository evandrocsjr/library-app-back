package com.library.api.domain.author.controller.v1;

import com.library.api.domain.author.controller.v1.dto.AuthorWebDTO;
import com.library.api.domain.author.service.AuthorService;
import com.library.api.domain.author.service.dto.AuthorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "v1/authors")
@RequiredArgsConstructor
@Tag(name = "Authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um novo autor.")
    public AuthorWebDTO createAuthor(@RequestBody AuthorWebDTO authorWebDTO) {
        ModelMapper mapper = new ModelMapper();
        AuthorDTO newAuthor = authorService.createNewAuthor(mapper.map(authorWebDTO, AuthorDTO.class));
        return mapper.map(newAuthor, AuthorWebDTO.class);
    }

    @GetMapping("/getAllByLicense")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Recupera todos os autores da licença.")
    public List<AuthorWebDTO> getAllByLicense() {
        List<AuthorDTO> authorList = authorService.getAllAuthorsByLicense();
        ModelMapper mapper = new ModelMapper();
        return authorList.stream().map(aut -> mapper.map(aut, AuthorWebDTO.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Recupera um autor através do id.")
    public AuthorWebDTO getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        ModelMapper mapper = new ModelMapper();
        return mapper.map(author, AuthorWebDTO.class);
    }

//    @PutMapping(value = "/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public AuthorWebDTO update(@PathVariable long id,
//                               @RequestBody AuthorWebDTO authorWebDTO) {
//
//    }
}
