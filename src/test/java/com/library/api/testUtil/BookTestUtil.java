package com.library.api.testUtil;

import com.library.api.domain.book.repository.entity.Book;
import com.library.api.domain.book.repository.enums.BookAvailability;

public class BookTestUtil {

    public static Book createBookToSave() {
        return Book
                .builder()
                .name("Livro 1")
                .bookAvailability(BookAvailability.UNAVAILABLE)
                .description("Descrição do livro teste")
                .build();
    }
}
