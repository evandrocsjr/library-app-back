package com.library.api.book.controller.v1;

import com.library.api.domain.book.controller.v1.dto.BookWebDTO;
import com.library.api.domain.book.repository.BookRepository;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.testUtil.BookTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Objects;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void create_success() {
        HttpHeaders auth = new HttpHeaders();
        Book book = BookTestUtil.createBookToSave();
        ResponseEntity<BookWebDTO> response = testRestTemplate.exchange("/v1/books", HttpMethod.POST,
                new HttpEntity<>(new ModelMapper().map(book, BookWebDTO.class), auth), BookWebDTO.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void getAll() {
        HttpHeaders auth = new HttpHeaders();

    }

    @Test
    void getByIds_success() {
        Book book = bookRepository.save(BookTestUtil.createBookToSave());
        HttpHeaders auth = new HttpHeaders();
        ResponseEntity<BookDTO> response = testRestTemplate.exchange("/v1/books/" + book.getId(), HttpMethod.GET,
                new HttpEntity<>(null, auth), BookDTO.class);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(response.getBody().getId(), book.getId());
    }

    @Test
    void delete_success() {
        Book book = bookRepository.save(BookTestUtil.createBookToSave());
        HttpHeaders auth = new HttpHeaders();
        ResponseEntity<String> response = testRestTemplate.exchange("/v1/books/" + book.getId(), HttpMethod.DELETE,
                new HttpEntity<>(null, auth), String.class);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
