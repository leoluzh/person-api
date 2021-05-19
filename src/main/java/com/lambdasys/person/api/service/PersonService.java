package com.lambdasys.person.api.service;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import com.lambdasys.person.api.exception.PersonNotFoundException;
import com.lambdasys.person.api.mapper.PersonMapper;
import com.lambdasys.person.api.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public PersonDto create(PersonDto personDto) {
        return save(personDto);
    }

    public List<PersonDto> findAll() {
        return personRepository
                .findAll()
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

    public PersonDto findById(Long id) throws PersonNotFoundException {
        var person = verifyIfExists(id);
        return personMapper.toDto(person);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public PersonDto updateById(Long id, final PersonDto personDto) throws PersonNotFoundException {
        verifyIfExists(id);
        return save(personDto);
    }

    private PersonDto save(PersonDto personDto) {
        var person = personMapper.toModel(personDto);
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

}
