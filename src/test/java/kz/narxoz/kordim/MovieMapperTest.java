package kz.narxoz.kordim;

import kz.narxoz.kordim.dto.CountryDto;
import kz.narxoz.kordim.dto.GenreDto;
import kz.narxoz.kordim.dto.MovieDto;
import kz.narxoz.kordim.entity.Country;
import kz.narxoz.kordim.entity.Genre;
import kz.narxoz.kordim.entity.Movie;
import kz.narxoz.kordim.mapper.MovieMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MovieMapperTest {
    @Autowired
    private MovieMapper movieMapper;

    @Test
    void toDtoTest(){
        Country country = new Country(1L, "USA");
        Genre genre1 = new Genre(1L, "Action");
        Genre genre2 = new Genre(2L, "Drama");

        Movie movie = new Movie(1L, "Matrix", 2000, country, Arrays.asList(genre1, genre2));

        MovieDto dto = movieMapper.toDto(movie);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(movie.getTitle(), dto.getTitleDto());
        Assertions.assertEquals(movie.getYear(), dto.getYearDto());
        Assertions.assertNotNull(dto.getCountryDto());
        Assertions.assertEquals(movie.getCountry().getName(), dto.getCountryDto().getNameDto());
        Assertions.assertEquals(2, dto.getGenreDtos().size());
    }

    @Test
    void toEntityTest(){
        CountryDto countryDto = new CountryDto(1L, "KAZ");
        GenreDto g1 = new GenreDto(1L, "Horror");
        GenreDto g2 = new GenreDto(2L, "Comedy");

        MovieDto movieDto = new MovieDto(1L,"Narxoz", 2023, countryDto, Arrays.asList(g1, g2));

        Movie entity = movieMapper.toEntity(movieDto);

        Assertions.assertNotNull(entity);
        Assertions.assertEquals(movieDto.getTitleDto(), entity.getTitle());
        Assertions.assertEquals(movieDto.getYearDto(), entity.getYear());
        Assertions.assertNotNull(entity.getCountry());
        Assertions.assertEquals(movieDto.getCountryDto().getNameDto(), entity.getCountry().getName());
        Assertions.assertEquals(2, entity.getGenres().size());
    }

    @Test
    void toDtoListTest(){
        Movie m1 = new Movie(1L, "Avatar", 2012, null, null);
        Movie m2 = new Movie(2L, "Women", 2022, null, null);

        List<MovieDto> list = movieMapper.toDtoList(Arrays.asList(m1, m2));

        Assertions.assertNotNull(list);
        Assertions.assertEquals("Avatar", list.get(0).getTitleDto());
        Assertions.assertEquals("Women", list.get(1).getTitleDto());
    }
}