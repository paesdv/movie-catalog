package com.paes.movie_catalog.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    @Size(max = 200)
    private String title;


    @Size(max = 1000)
    private String description;

    private Integer releaseYear;

    private String genre;

    private String director;

    private Double rating;

    private String posterUrl;

    private LocalDateTime createdAt;

}
