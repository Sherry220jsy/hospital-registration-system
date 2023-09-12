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
public class DoctorDTO implements Serializable {

    private Long doctorId;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private String department;

    private String specialization;

    private String position;

}
