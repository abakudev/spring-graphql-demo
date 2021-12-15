package com.gl.demo.graphql.service.impl;

import com.gl.demo.graphql.dto.AuthorDTO;
import com.gl.demo.graphql.model.Author;
import com.gl.demo.graphql.repository.AuthorRepository;
import com.gl.demo.graphql.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<AuthorDTO> getAuthors() {
        List<Author> authors = this.authorRepository.findAll();
        return authors.stream().map(
                author -> AuthorDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .email(author.getEmail())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDTO getAuthorById(UUID authorId){
        Optional<Author> author = this.authorRepository.findById(authorId);
        return author.map(value -> AuthorDTO.builder()
                .id(authorId)
                .name(value.getName())
                .email(value.getEmail())
                .build()).orElse(null);
    }

}
