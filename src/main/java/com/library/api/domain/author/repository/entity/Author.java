package com.library.api.domain.author.repository.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "author")
@Builder
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private Date createdAt;

    @Column
    private Date dateOfBirth;
}
