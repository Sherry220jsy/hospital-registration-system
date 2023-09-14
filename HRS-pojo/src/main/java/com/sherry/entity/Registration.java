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
public class Registration implements Serializable {

    private Long registrationId;
    private Long doctorId;
    private Long patientId;
    private Long modelCategoryId;
    private Long categoryId;
    private Integer status;
    private String visitStartTime;
    private String visitEndTime;
    private String date;
}

