package com.lambdasys.person.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@SuppressWarnings("serial")
public class PersonDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(min=2,max=50)
    private String name;

    @NotBlank
    @Size(min=1,max=255)
    private String surname;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    @PastOrPresent
    private String birthdate;

    @Valid
    @NotEmpty
    private List<PhoneDto> phones;

}
