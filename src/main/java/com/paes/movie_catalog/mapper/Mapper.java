package com.paes.movie_catalog.mapper;

import com.paes.movie_catalog.dto.MovieRequestDTO;
import com.paes.movie_catalog.dto.MovieResponseDTO;
import com.paes.movie_catalog.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Movie toEntity(MovieRequestDTO dto){
        return Movie.builder()
                .title(dto.title())
                .description(dto.description())
                .releaseYear(dto.releaseYear())
                .genre(dto.genre())
                .director(dto.director())
                .rating(dto.rating())
                .posterUrl(dto.posterUrl())
                .build();
    }

    public MovieResponseDTO toResponseDTO(Movie movie) {
        return new MovieResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDescription(),
                movie.getReleaseYear(),
                movie.getGenre(),
                movie.getDirector(),
                movie.getRating(),
                movie.getPosterUrl(),
                movie.getCreatedAt()
        );
    }

    public void updateEntityFromDTO(MovieRequestDTO dto, Movie movie) {
        movie.setTitle(dto.title());
        movie.setDescription(dto.description());
        movie.setReleaseYear(dto.releaseYear());
        movie.setGenre(dto.genre());
        movie.setDirector(dto.director());
        movie.setRating(dto.rating());
        movie.setPosterUrl(dto.posterUrl());
    }



}
