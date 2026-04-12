package com.paes.movie_catalog.dto;


import jakarta.validation.constraints.*;

public record MovieRequestDTO(

        @NotBlank
        @Size(max = 200)
        String title,

        @NotBlank
        @Size(max = 500)
        String description,

        @NotNull
        Integer releaseYear,

        @NotBlank
        String genre,

        @NotBlank
        @Size(max = 100)
        String director,

        @NotNull
        @DecimalMin(value = "0.0", message = "A nota mínima é de 0.0." )
        @DecimalMax(value = "10.0", message = "a nota máxima é 10.")
        Double rating,

        String posterUrl

) {
}
