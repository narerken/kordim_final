package kz.narxoz.kordim.service.impl;

import kz.narxoz.kordim.dto.CountryDto;
import kz.narxoz.kordim.entity.Country;
import kz.narxoz.kordim.mapper.CountryMapper;
import kz.narxoz.kordim.repository.CountryRepository;
import kz.narxoz.kordim.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    public List<CountryDto> getAll(){
        return countryMapper.toDtoList(countryRepository.findAll());
    }

    @Override
    public void addCountry(CountryDto countryDto){
        Country country = countryMapper.toEntity(countryDto);
        countryRepository.save(country);
    }

    @Override
    public void updateCountry(Long id, CountryDto countryDto){
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return;
        }

        country.setName(countryDto.getNameDto());
        countryRepository.save(country);
    }

    @Override
    public boolean deleteCountry(Long id){
        if(!countryRepository.existsById(id)){
            return false;
        }
        countryRepository.deleteById(id);
        return true;
    }
}
