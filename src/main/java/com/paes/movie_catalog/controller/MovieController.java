package com.paes.movie_catalog.controller;

import com.paes.movie_catalog.dto.MovieRequestDTO;
import com.paes.movie_catalog.dto.MovieResponseDTO;
import com.paes.movie_catalog.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<Page<MovieResponseDTO>> searchByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(movieService.searchByTitle(title, pageable));
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> create(@RequestBody @Valid MovieRequestDTO dto){
        return ResponseEntity.status(201).body(movieService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update(@PathVariable Long id, @RequestBody @Valid MovieRequestDTO dto){
        return ResponseEntity.ok(movieService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
