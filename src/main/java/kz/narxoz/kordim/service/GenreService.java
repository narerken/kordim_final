package kz.narxoz.kordim.service;

import kz.narxoz.kordim.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
    void addGenre(GenreDto genreDto);
    void updateGenre(Long id, GenreDto genreDto);
    boolean deleteGenre(Long id);
}
