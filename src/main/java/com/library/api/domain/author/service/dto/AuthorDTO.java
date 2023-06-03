package com.library.api.domain.author.service.dto;

import lombok.*;

import java.util.Date;

@Builder
@Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    private String description;
    private Date createdAt;
}
