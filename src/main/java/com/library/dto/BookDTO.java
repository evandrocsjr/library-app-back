package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BookDTO {

    private Long bookId;
    private String bookName;
}
