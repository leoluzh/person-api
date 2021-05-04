package com.lambdasys.person.api.mapper;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;

public interface PersonMapper {

    PersonMapper INSTANCE = null;

    Person toEntity(PersonDto personDto);
    PersonDto toDto(Person person);

}
