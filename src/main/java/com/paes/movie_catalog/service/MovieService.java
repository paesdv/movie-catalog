package com.paes.movie_catalog.service;


import com.paes.movie_catalog.dto.MovieRequestDTO;
import com.paes.movie_catalog.dto.MovieResponseDTO;
import com.paes.movie_catalog.exception.MovieNotFoundException;
import com.paes.movie_catalog.mapper.Mapper;
import com.paes.movie_catalog.model.Movie;
import com.paes.movie_catalog.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final Mapper mapper;

    public MovieService (MovieRepository movieRepository, Mapper mapper){
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    public List<MovieResponseDTO> findAll(){
        return movieRepository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public MovieResponseDTO findById(Long id){
        Movie movie = movieRepository.findById(id).
                orElseThrow(() -> new MovieNotFoundException(id));
        return mapper.toResponseDTO(movie);
    }

    public Page<MovieResponseDTO> searchByTitle(String title, Pageable pageable){
        return movieRepository.findByTitleContainingIgnoreCase(title, pageable)
                .map(mapper::toResponseDTO);
    }

    public MovieResponseDTO create(MovieRequestDTO dto){
        Movie movie = mapper.toEntity(dto);
        Movie saved = movieRepository.save(movie);

        return mapper.toResponseDTO(saved);
    }

    public MovieResponseDTO update(Long id, MovieRequestDTO dto){
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
        mapper.updateEntityFromDTO(dto, movie);
        Movie updated = movieRepository.save(movie);

        return mapper.toResponseDTO(updated);

    }

    public void delete(Long id){
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundException(id);
        }
        movieRepository.deleteById(id);
    }

}
