package com.paes.movie_catalog.dto;

public record AuthResponseDTO(
        String token,
        String username,
        String role
) {
}
