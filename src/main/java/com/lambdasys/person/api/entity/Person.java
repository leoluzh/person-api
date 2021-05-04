package com.lambdasys.person.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@SuppressWarnings("serial")
public class Person implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String cpf;
    private LocalDate birthdate;

}
