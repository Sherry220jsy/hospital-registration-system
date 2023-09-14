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
public class PatientDoctorVO implements Serializable {
    private Long doctorId;

    private String name;

    private String sex;

    private String department;

    private String specialization;

    private String position;
}
