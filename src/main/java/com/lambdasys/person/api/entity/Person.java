package com.lambdasys.person.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="person")
@SuppressWarnings("serial")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",length=50)
    private String name;

    @Column(name="surname",length=150)
    private String surname;

    @Column(name="cpf",length = 13)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name="birthdate")
    private LocalDate birthdate;

}
