package com.sherry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient  implements Serializable {

    private Long patientId;

    private String username;

    private String password;

    private String name;

    private String phone;

    private String sex;

    private String idNumber;

    private Integer noShowCount;
}
