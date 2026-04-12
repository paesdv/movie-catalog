package com.paes.movie_catalog.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException (Long id){
        super("Filme não encontrado com id: " + id);
    }

}
