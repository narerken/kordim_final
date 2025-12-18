package kz.narxoz.kordim.service.impl;

import kz.narxoz.kordim.dto.GenreDto;
import kz.narxoz.kordim.entity.Genre;
import kz.narxoz.kordim.mapper.GenreMapper;
import kz.narxoz.kordim.repository.GenreRepository;
import kz.narxoz.kordim.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAll(){
        return genreMapper.toDtoList(genreRepository.findAll());
    }

    @Override
    public void addGenre(GenreDto genreDto){
        Genre genre = genreMapper.toEntity(genreDto);
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Long id, GenreDto genreDto){
        Genre genre = genreRepository.findById(id).orElse(null);
        if(genre == null){
            return;
        }
        genre.setName(genreDto.getNameDto());
        genreRepository.save(genre);
    }

    @Override
    public boolean deleteGenre(Long id){
        if (!genreRepository.existsById(id)){
            return false;
        }
        genreRepository.deleteById(id);
        return true;
    }
}
