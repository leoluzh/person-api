package com.lambdasys.person.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @Column(name="name",length=50,nullable = false)
    private String name;

    @Column(name="surname",length=150,nullable = false)
    private String surname;

    @Column(name="cpf",length = 13, unique = true, nullable = false)
    private String cpf;

    @Column(name="birthdate")
    private LocalDate birthdate;

    @OneToMany(
            fetch = FetchType.LAZY ,
            cascade = CascadeType.ALL ,
            orphanRemoval = true)
    private List<Phone> phones;

}
