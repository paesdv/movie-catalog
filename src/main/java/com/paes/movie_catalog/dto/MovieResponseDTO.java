package com.paes.movie_catalog.dto;

import java.time.LocalDateTime;

public record MovieResponseDTO(
        Long id,
        String title,
        String description,
        Integer releaseYear,
        String genre,
        String director,
        Double rating,
        String posterUrl,
        LocalDateTime createdAt
) {
}
