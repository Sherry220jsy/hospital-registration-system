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
public class PatientUpdateDTO implements Serializable {
    private Long patientId;

    private String idNumber;

    private String name;

    private String phone;

    private String sex;

}
