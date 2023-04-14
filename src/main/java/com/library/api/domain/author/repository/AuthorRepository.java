package com.library.api.domain.author.repository;

import com.library.api.domain.author.repository.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
