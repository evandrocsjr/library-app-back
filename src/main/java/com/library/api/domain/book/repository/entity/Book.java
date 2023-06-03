package com.library.api.domain.book.repository.entity;

import com.library.api.domain.author.repository.entity.Author;
import com.library.api.domain.book.repository.enums.BookAvailability;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "book")
@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private BookAvailability bookAvailability;

    @Column
    private Date createdAt;

    @Column
    private Date releaseDate;

    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "books_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "author_id", nullable = false))
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Author> authors;
}
