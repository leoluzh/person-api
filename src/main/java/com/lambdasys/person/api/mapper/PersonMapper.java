package com.lambdasys.person.api.mapper;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    String DATE_FORMAT = "dd-MM-yyyy";
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthdate",source = "birthdate",dateFormat = DATE_FORMAT)
    Person toModel(PersonDto personDto);

    PersonDto toDto(Person person);

}
