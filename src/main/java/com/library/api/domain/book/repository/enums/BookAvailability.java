package com.library.api.domain.book.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookAvailability {
    AVAILABLE(1, "Disponível"),
    UNAVAILABLE(2, "Indisponível");

    private final int value;
    private final String description;
}
