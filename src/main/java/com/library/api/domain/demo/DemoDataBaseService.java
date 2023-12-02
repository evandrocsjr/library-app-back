package com.library.api.domain.demo;

import com.library.api.domain.book.repository.BookRepository;
import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.enums.BookAvailability;
import com.library.api.domain.user.repository.UserRepository;
import com.library.api.domain.user.repository.entity.User;
import com.library.api.domain.user.repository.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Deprecated
@Service
@RequiredArgsConstructor
public class DemoDataBaseService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    public void createUserAndBooks() {
        if(userRepository.findAll().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode("147258369");
            User newUser = User.builder()
                    .role(UserRole.ADMIN)
                    .email("evandro@gmail.com")
                    .password(encryptedPassword)
                    .build();
            userRepository.save(newUser);
            createBooks();
        }
    }

    public void createBooks() {
        for(int i = 0; i<= 20; i++) {
            Calendar releaseDate = Calendar.getInstance();
            releaseDate.add(Calendar.DATE, -20);

            Book book = Book
                    .builder()
                    .bookAvailability(BookAvailability.AVAILABLE)
                    .name("Livro " + i)
                    .createdAt(new Date())
                    .releaseDate(releaseDate.getTime())
                    .description("Descrição do livro " + i)
                    .build();
            bookRepository.save(book);
        }
    }
}
