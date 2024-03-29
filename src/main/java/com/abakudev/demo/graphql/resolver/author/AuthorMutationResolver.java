package com.abakudev.demo.graphql.resolver.author;

import com.abakudev.demo.graphql.dto.AuthorDTO;
import com.abakudev.demo.graphql.service.AuthorService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {

    private final AuthorService authorService;

    @Autowired
    public AuthorMutationResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public UUID createAuthor(AuthorDTO author){
        return this.authorService.createAuthor(author);
    }

}
