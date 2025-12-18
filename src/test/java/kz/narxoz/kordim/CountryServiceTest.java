package kz.narxoz.kordim;

import kz.narxoz.kordim.dto.CountryDto;
import kz.narxoz.kordim.service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllTest() {
        List<CountryDto> countries = countryService.getAll();
        Assertions.assertNotNull(countries);
        Assertions.assertFalse(countries.isEmpty());

        for (CountryDto country : countries) {
            Assertions.assertNotNull(country);
            Assertions.assertNotNull(country.getNameDto());
        }
    }

    @Test
    void addCountryTest() {
        CountryDto countryDto = new CountryDto();
        countryDto.setNameDto("Test Country");

        countryService.addCountry(countryDto);

        List<CountryDto> countries = countryService.getAll();
        boolean exists = countries.stream()
                .anyMatch(c -> "Test Country".equals(c.getNameDto()));

        Assertions.assertTrue(exists);
    }

    @Test
    void updateCountryTest() {
        List<CountryDto> countries = countryService.getAll();
        Random random = new Random();
        CountryDto country = countries.get(random.nextInt(countries.size()));

        CountryDto updateDto = new CountryDto();
        updateDto.setNameDto("Updated Country");

        countryService.updateCountry(country.getId(), updateDto);

        CountryDto updated = countryService.getAll().stream()
                .filter(c -> c.getId().equals(country.getId()))
                .findFirst()
                .orElse(null);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Country", updated.getNameDto());
    }

    @Test
    void deleteCountryTest() {
        List<CountryDto> countries = countryService.getAll();
        Random random = new Random();
        Long id = countries.get(random.nextInt(countries.size())).getId();

        boolean deleted = countryService.deleteCountry(id);
        Assertions.assertTrue(deleted);

        boolean exists = countryService.getAll().stream()
                .anyMatch(c -> c.getId().equals(id));

        Assertions.assertFalse(exists);
    }
}
