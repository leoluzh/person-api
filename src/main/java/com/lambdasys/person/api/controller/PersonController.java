package com.lambdasys.person.api.controller;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import com.lambdasys.person.api.mapper.PersonMapper;
import com.lambdasys.person.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonRepository repository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonController(PersonRepository repository){
        this.repository = repository;
    }

    @PostMapping()
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto){
        Person person = personMapper.toEntity(personDto);
        person = repository.save(person);
        return ResponseEntity.ok(personMapper.toDto(person));
    }

}
