package com.sherry.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorVO {
    private Long id;

    private String name;

    private String sex;

    private String department;

    private String specialization;

    private String position;

}
