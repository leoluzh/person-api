package com.lambdasys.person.api.mapper;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person toEntity(PersonDto personDto);
    PersonDto toDto(Person person);

}
