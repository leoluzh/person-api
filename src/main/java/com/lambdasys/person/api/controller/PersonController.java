package com.lambdasys.person.api.controller;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.exception.PersonNotFoundException;
import com.lambdasys.person.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDto> create(@RequestBody @Valid PersonDto personDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personService.create(personDto));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<PersonDto>> listAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        return ResponseEntity.ok(personService.findById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws PersonNotFoundException {
        personService.deleteById(id);
    }

}
