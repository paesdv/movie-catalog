package com.paes.movie_catalog.service;


import com.paes.movie_catalog.dto.MovieResponseDTO;
import com.paes.movie_catalog.model.Movie;
import com.paes.movie_catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }

    public MovieResponseDTO findById(Long id){
        Movie movie = movieRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Filme não encontrado."));
        return mapper.toResponseDTO(movie);
    }

}
