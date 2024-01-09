package com.library.api.book.controller.v1;

import com.library.api.domain.author.controller.v1.dto.AuthorWebDTO;
import com.library.api.domain.author.repository.entity.Author;
import com.library.api.domain.book.controller.v1.dto.BookWebDTO;
import com.library.api.domain.book.repository.BookRepository;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.enums.BookAvailability;
import com.library.api.domain.book.service.dto.BookDTO;
import com.library.api.domain.security.TokenService;
import com.library.api.domain.user.repository.UserRepository;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.security.TokenServiceTest;
import com.library.api.testUtil.AuthorTestUtil;
import com.library.api.testUtil.BookTestUtil;
import com.library.api.testUtil.UserTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TokenServiceTest tokenServiceTest;

    @Test
    void create_success() {
        HttpHeaders auth = new HttpHeaders();
        User newUser = UserTestUtil.createAdminUser(userRepository);
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));

        Book book = BookTestUtil.createBookToSave();
        ResponseEntity<BookWebDTO> response = testRestTemplate.exchange("/v1/books", HttpMethod.POST,
                new HttpEntity<>(new ModelMapper().map(book, BookWebDTO.class), auth), BookWebDTO.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertNotNull(Objects.requireNonNull(response.getBody()).getId());
    }

    @Test
    void create_noBearerTokenException() {
        Book book = BookTestUtil.createBookToSave();
        ResponseEntity<BookWebDTO> response = testRestTemplate.exchange("/v1/books", HttpMethod.POST,
                new HttpEntity<>(new ModelMapper().map(book, BookWebDTO.class), new HttpHeaders()), BookWebDTO.class);

        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void create_unauthorizedException() {
        Author author = AuthorTestUtil.createAuthorToSave();
        HttpHeaders auth = new HttpHeaders();
        User newUser = UserTestUtil.createBasicUser(userRepository);
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));

        ResponseEntity<AuthorWebDTO> response = testRestTemplate.exchange("/v1/books", HttpMethod.POST,
                new HttpEntity<>(new ModelMapper().map(author, AuthorWebDTO.class), auth), AuthorWebDTO.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void getAllByLicense_success() {
        for (int i = 1; i <= 25; i++) {
            bookRepository.save(BookTestUtil.createBookToSave());
        }
        HttpHeaders auth = new HttpHeaders();
        User newUser = UserTestUtil.createAdminUser(userRepository);
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));

        ResponseEntity<List> response = testRestTemplate.exchange("/v1/books/getAllByLicense", HttpMethod.GET,
                new HttpEntity<>(null, auth), List.class);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(25, response.getBody().size());
    }

    @Test
    void getAll_success() {
        for (int i = 1; i <= 5; i++) {
            bookRepository.save(BookTestUtil.createBookToSave());
        }
        HttpHeaders auth = new HttpHeaders();
        User newUser = UserTestUtil.createAdminUser(userRepository);
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));

        ResponseEntity<List> response = testRestTemplate.exchange("/v1/books?page=1", HttpMethod.GET,
                new HttpEntity<>(null, auth), List.class);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(5, response.getBody().size());
    }

    @Test
    void getById_success() {
        Book book = bookRepository.save(BookTestUtil.createBookToSave());
        HttpHeaders auth = new HttpHeaders();
        User newUser = UserTestUtil.createAdminUser(userRepository);
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));

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

        auth.add("Authorization", tokenServiceTest.generateToken(UserTestUtil.createAdminUser(userRepository)));
        ResponseEntity<String> response = testRestTemplate.exchange("/v1/books/" + book.getId(), HttpMethod.DELETE,
                new HttpEntity<>(null, auth), String.class);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void update_success() {
        User newUser = UserTestUtil.createAdminUser(userRepository);
        HttpHeaders auth = new HttpHeaders();
        auth.add("Authorization", tokenServiceTest.generateToken(newUser));
        Book newBook = bookRepository.save(BookTestUtil.createBookToSave());
        Book bookToUpdate = BookTestUtil.createBookToSave();
        bookToUpdate.setName("Book name test");
        bookToUpdate.setDescription("Book description updated");
        bookToUpdate.setBookAvailability(BookAvailability.UNAVAILABLE);
        ResponseEntity<BookDTO> response = testRestTemplate.exchange("/v1/books/" + newBook.getId(), HttpMethod.PUT,
                new HttpEntity<>(bookToUpdate, auth), BookDTO.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertNotNull(response);

        BookDTO bookUpdated = response.getBody();
        Assertions.assertNotNull(bookUpdated);
        Assertions.assertEquals(bookUpdated.getBookAvailability(), bookToUpdate.getBookAvailability());
        Assertions.assertEquals(bookUpdated.getName(), bookToUpdate.getName());
        Assertions.assertEquals(bookUpdated.getCreatedAt(), bookToUpdate.getCreatedAt());
        Assertions.assertEquals(bookUpdated.getReleaseDate(), bookToUpdate.getReleaseDate());
    }
}
