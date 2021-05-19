package com.lambdasys.person.api.builder;

import com.lambdasys.person.api.dto.PersonDto;
import com.lambdasys.person.api.dto.PhoneDto;
import com.lambdasys.person.api.entity.Person;
import com.lambdasys.person.api.mapper.PersonMapper;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
public class PersonDtoBuilder {

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PersonMapper.DATE_FORMAT);

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "John";

    @Builder.Default
    private String surname = "Doe";

    @Builder.Default
    private String cpf = "211.977.870-11";

    @Builder.Default
    private String birthdate = "01-12-1991";

    @Builder.Default
    private List<PhoneDto> phones = new ArrayList<>();


    public Person toMovie(){

        return Person.builder()
                .id(id)
                .name(name)
                .surname(surname)
                .cpf(cpf)
                .birthdate(LocalDate.parse(birthdate,FORMATTER))
                .build();
    }

    public PersonDto toPersonDto(){
        return PersonMapper.INSTANCE.toDto(toMovie());
    }

}
