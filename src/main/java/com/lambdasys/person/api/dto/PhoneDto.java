package com.lambdasys.person.api.dto;

import com.lambdasys.person.api.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@SuppressWarnings("serial")
public class PhoneDto implements Serializable {

    private Long id;

    @NotNull
    private PhoneType type;

    @NotBlank
    @Size(min=13,max=20)
    private String number;

}
