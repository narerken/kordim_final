package kz.narxoz.kordim.service;

import kz.narxoz.kordim.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    void addCountry(CountryDto countryDto);
    void updateCountry(Long id, CountryDto countryDto);
    boolean deleteCountry(Long id);
}
