package kz.narxoz.kordim;

import kz.narxoz.kordim.dto.GenreDto;
import kz.narxoz.kordim.service.GenreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @Test
    void getAllTest() {
        List<GenreDto> genres = genreService.getAll();
        Assertions.assertNotNull(genres);
        Assertions.assertFalse(genres.isEmpty());

        for (GenreDto genre : genres) {
            Assertions.assertNotNull(genre);
            Assertions.assertNotNull(genre.getNameDto());
        }
    }

    @Test
    void addGenreTest() {
        GenreDto genreDto = new GenreDto();
        genreDto.setNameDto("Test Genre");

        genreService.addGenre(genreDto);

        List<GenreDto> genres = genreService.getAll();
        boolean exists = genres.stream()
                .anyMatch(g -> "Test Genre".equals(g.getNameDto()));

        Assertions.assertTrue(exists);
    }

    @Test
    void updateGenreTest() {
        List<GenreDto> genres = genreService.getAll();
        Random random = new Random();
        GenreDto genre = genres.get(random.nextInt(genres.size()));

        GenreDto updateDto = new GenreDto();
        updateDto.setNameDto("Updated Genre");

        genreService.updateGenre(genre.getId(), updateDto);

        GenreDto updated = genreService.getAll().stream()
                .filter(g -> g.getId().equals(genre.getId()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Genre", updated.getNameDto());
    }

    @Test
    void deleteGenreTest() {
        List<GenreDto> genres = genreService.getAll();
        Random random = new Random();
        Long id = genres.get(random.nextInt(genres.size())).getId();

        boolean deleted = genreService.deleteGenre(id);
        Assertions.assertTrue(deleted);

        boolean exists = genreService.getAll().stream()
                .anyMatch(g -> g.getId().equals(id));

        Assertions.assertFalse(exists);
    }
}
