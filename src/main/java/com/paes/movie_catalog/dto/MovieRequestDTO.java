package com.paes.movie_catalog.dto;

public record MovieRequestDTO(
        String title,
        String description,
        Integer releaseYear,
        String genre,
        String director,
        Double rating,
        String posterUrl
) {
}
