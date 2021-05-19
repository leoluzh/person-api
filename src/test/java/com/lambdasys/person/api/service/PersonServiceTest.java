package com.lambdasys.person.api.service;

import com.lambdasys.person.api.builder.PersonDtoBuilder;
import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.entity.Person;
import com.lambdasys.person.api.exception.PersonNotFoundException;
import com.lambdasys.person.api.mapper.PersonMapper;
import com.lambdasys.person.api.repository.PersonRepository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    private PersonMapper personMapper = PersonMapper.INSTANCE;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    public void beforeEach(){

    }

    @AfterEach
    public void afterEach(){

    }

    @Order(1)
    @DisplayName("When person is informed then it should be created")
    @Test
    public void whenPersonIsInformedThenItShouldBeCreated(){

        // given
        PersonDto expectedPersonDto = PersonDtoBuilder.builder().build().toPersonDto();
        Person expectedSavePerson = personMapper.toModel(expectedPersonDto);

        // when
        //when(personRepository.findById(expectedPersonDto.getId())).thenReturn(Optional.empty());
        when(personRepository.save(expectedSavePerson)).thenReturn(expectedSavePerson);

        // then
        PersonDto createdPersonDto = personService.create( expectedPersonDto );

        assertThat( createdPersonDto.getId() , Matchers.is( Matchers.equalTo( expectedPersonDto.getId() )));
        assertThat( createdPersonDto.getName() , Matchers.is( Matchers.equalTo( expectedPersonDto.getName() )));
        assertThat( createdPersonDto.getSurname() , Matchers.is( Matchers.equalTo( expectedPersonDto.getSurname() )));
        assertThat( createdPersonDto.getCpf() , Matchers.is( Matchers.equalTo( expectedPersonDto.getCpf() )));
        assertThat( createdPersonDto.getBirthdate() , Matchers.is( Matchers.equalTo( expectedPersonDto.getBirthdate() )));

    }

    @Order(2)
    @DisplayName("When a valid person id is given the return a person")
    @Test
    public void whenValidPersonIdIsGivenThenReturnAPerson() throws PersonNotFoundException {

        // given
        PersonDto expectedFoundPersonDto = PersonDtoBuilder.builder().build().toPersonDto();
        Person expectedFoundPerson = personMapper.toModel( expectedFoundPersonDto );

        // when
        when(personRepository.findById(expectedFoundPerson.getId())).thenReturn(Optional.of(expectedFoundPerson));

        // then
        PersonDto foundPersonDto = personService.findById(expectedFoundPersonDto.getId());

        assertThat(foundPersonDto,is(equalTo(expectedFoundPersonDto)));

    }

    @Order(3)
    @DisplayName("When not registered person id is given then throw an exception")
    @Test
    public void whenNotRegisteredPersonIdIsGivenThenThrowAnException(){

        // given
        PersonDto expectedFoundPersonDto = PersonDtoBuilder.builder().build().toPersonDto();

        // when
        when(personRepository.findById(expectedFoundPersonDto.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(PersonNotFoundException.class, () -> personService.findById(expectedFoundPersonDto.getId()));

    }

    @Order(4)
    @DisplayName("When list person is called then return a list of persons")
    @Test
    public void whenListPersonIsCalledThenReturnAListOfPersons(){

        // given -
        PersonDto expectedFoundPersonDto = PersonDtoBuilder.builder().build().toPersonDto();
        Person expectedFoundPerson = personMapper.toModel( expectedFoundPersonDto );

        // when
        when(personRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPerson));

        // then
        List<PersonDto> foundListPersonsDto = personService.findAll();

        assertThat(foundListPersonsDto, is(not(empty())));
        assertThat(foundListPersonsDto.get(0), is(equalTo(expectedFoundPersonDto)));

    }

    @Order(5)
    @DisplayName("When list movie is called then return an empty list of movies")
    @Test
    public void whenListMovieIsCalledThenReturnAnEmptyListOfMovies(){

        // when
        when(personRepository.findAll()).thenReturn(Collections.emptyList());

        // then
        List<PersonDto> foundListPersonsDto = personService.findAll();

        assertThat(foundListPersonsDto,is(empty()));

    }

    @Order(6)
    @DisplayName("When delete is called with a valid id then a person should be deleted")
    @Test
    public void whenDeleteIsCalledWithValidIdThenPersonShouldBeDeleted() throws PersonNotFoundException {

        // given
        PersonDto expectedDeletedPersonDto = PersonDtoBuilder.builder().build().toPersonDto();
        Person expectedDeletePerson = personMapper.toModel( expectedDeletedPersonDto );

        // when
        when(personRepository.findById(expectedDeletedPersonDto.getId())).thenReturn(Optional.of(expectedDeletePerson));

        // then
        personService.deleteById(expectedDeletedPersonDto.getId());

        //how to check a void/one way function - use times to check if pass
        verify(personRepository,times(1)).findById(expectedDeletedPersonDto.getId());
        verify(personRepository,times(1)).deleteById(expectedDeletedPersonDto.getId());

    }

    @Order(7)
    @DisplayName("When delete is called with a invalid id then an exception should be throw")
    @Test
    public void whenDeleteIsCalledWithInvalidIdThenExceptionShouldThrow() throws PersonNotFoundException {

        // given
        PersonDto expectedDeletedPersonDto = PersonDtoBuilder.builder().build().toPersonDto();

        // when
        when(personRepository.findById(expectedDeletedPersonDto.getId())).thenReturn(Optional.empty());

        // then
        assertThrows(PersonNotFoundException.class, () -> personRepository.deleteById(expectedDeletedPersonDto.getId()));

    }
    
}
