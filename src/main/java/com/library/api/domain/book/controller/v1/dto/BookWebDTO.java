package com.library.api.domain.book.controller.v1.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookWebDTO {
    private Long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date releaseDate;

}
