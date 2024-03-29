package com.library.api.domain.book.service.dto;

import com.library.api.domain.author.service.dto.AuthorDTO;
import com.library.api.domain.book.repository.enums.BookAvailability;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookDTO {

    private Long id;
    private String name;
    private String description;
    private BookAvailability bookAvailability;
    private Date createdAt;
    private Date releaseDate;
    private List<AuthorDTO> authors;

}
