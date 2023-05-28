package com.library.api.domain.book.controller.v1.dto;

import com.library.api.domain.dto.SimpleIdDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private List<SimpleIdDTO> authors;

}
