package kz.narxoz.kordim.mapper;

import kz.narxoz.kordim.dto.CountryDto;
import kz.narxoz.kordim.entity.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(source = "name", target = "nameDto")
    CountryDto toDto(Country country);

    @Mapping(source = "nameDto", target = "name")
    Country toEntity(CountryDto countryDto);

    List<CountryDto> toDtoList(List<Country> countries);
}
