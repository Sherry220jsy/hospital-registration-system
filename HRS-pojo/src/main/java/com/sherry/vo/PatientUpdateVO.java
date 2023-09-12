package com.sherry.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientUpdateVO implements Serializable {
    private Long patientId;

    private String name;

    private String sex;

    private String phone;

}
