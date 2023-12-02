package com.library.api.domain.author.controller.v1.dto;

import lombok.*;

import java.util.Date;

@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class AuthorWebDTO {

    private Long id;
    private String name;
    private Date dateOfBirth;
    private String description;
    private Date createdAt;
}
