package com.lambdasys.person.api.service;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import com.lambdasys.person.api.mapper.PersonMapper;
import com.lambdasys.person.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public PersonDto create(PersonDto personDto){
        Person person = personMapper.toModel(personDto);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    public List<PersonDto> findAll(){
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::toDto)
                .collect(Collectors.toList());
    }

}
