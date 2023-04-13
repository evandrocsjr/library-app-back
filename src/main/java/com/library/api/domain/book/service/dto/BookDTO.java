package com.library.api.domain.book.service.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookDTO {

    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date releaseDate;

}
