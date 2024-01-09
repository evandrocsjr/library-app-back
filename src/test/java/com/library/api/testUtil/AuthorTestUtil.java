package com.library.api.testUtil;

import com.library.api.domain.author.repository.entity.Author;

import java.util.Date;

public class AuthorTestUtil {

    public static Author createAuthorToSave() {
        return Author.builder()
                .name("Author test")
                .dateOfBirth(new Date())
                .description("Description Test")
                .build();
    }
}
