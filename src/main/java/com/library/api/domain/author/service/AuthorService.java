package com.library.api.domain.author.service;

import com.library.api.domain.DefaultService;
import com.library.api.domain.author.repository.AuthorRepository;
import com.library.api.domain.author.repository.entity.Author;
import com.library.api.domain.author.service.dto.AuthorDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService extends DefaultService {

    private final AuthorRepository authorRepository;

    public AuthorDTO createNewAuthor(AuthorDTO newAuthor) {
        ModelMapper mapper = new ModelMapper();
        newAuthor.setCreatedAt(new Date());
        Author authorDb = authorRepository.save(mapper.map(newAuthor, Author.class));
        return mapper.map(authorDb, AuthorDTO.class);
    }

    @Transactional
    public List<AuthorDTO> getAllAuthorsByLicense() {
        ModelMapper mapper = new ModelMapper();
        List<Author> authorDbList = authorRepository.findAll();
        return authorDbList.stream().map(aut -> mapper.map(aut, AuthorDTO.class)).collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long authorId) {
        ModelMapper mapper = new ModelMapper();
        Optional<Author> authorDb = authorRepository.findById(authorId);
//        if (authorDb == null) new NotFoundException(RESOURCE_NOT_FOUND);
        return mapper.map(authorDb, AuthorDTO.class);
    }
}
