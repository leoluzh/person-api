package com.lambdasys.person.api.entity;

import com.lambdasys.person.api.enums.PhoneType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Phone implements Serializable {

    private Long id;
    private PhoneType type;
    private String number;

}
