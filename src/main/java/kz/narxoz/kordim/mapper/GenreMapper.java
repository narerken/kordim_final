package kz.narxoz.kordim.mapper;

import kz.narxoz.kordim.dto.GenreDto;
import kz.narxoz.kordim.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    @Mapping(source = "name", target = "nameDto")
    GenreDto toDto(Genre genre);

    @Mapping(source = "nameDto", target = "name")
    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toDtoList(List<Genre> genres);
}
