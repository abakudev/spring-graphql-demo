package com.abakudev.demo.graphql.exception.handler;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomGraphErrorHandler implements GraphQLErrorHandler {

    @Override
    public boolean errorsPresent(List<GraphQLError> errors) {
        return GraphQLErrorHandler.super.errorsPresent(errors);
    }

    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        return errors.stream().map(this::getNested).collect(Collectors.toList());
    }

    private GraphQLError getNested(GraphQLError graphQLError) {
        if(graphQLError instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching ex = (ExceptionWhileDataFetching) graphQLError;
            if(ex.getException() instanceof GraphQLError){
                return (GraphQLError) ex.getException();
            }
        }
        return graphQLError;
    }
}
