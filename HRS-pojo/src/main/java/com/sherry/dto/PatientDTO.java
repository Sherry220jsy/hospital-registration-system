package com.sherry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDTO implements Serializable {

    private Long id;

    private String name;

    private  String username;

    private String password;

    private String sex;

    private String idNumber;
}
