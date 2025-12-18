package kz.narxoz.kordim.service;

import kz.narxoz.kordim.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAll();
    MovieDto getById(Long id);
    MovieDto addMovie(MovieDto movieDto);
    MovieDto updateMovie(Long id, MovieDto movieDto);
    boolean deleteMovie(Long id);
}