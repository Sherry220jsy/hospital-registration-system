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
public class PatientRegistrationVO implements Serializable {

    private Long registrationId;

    private Long doctorId;

    private Long modelCategoryId;

    private Integer status;

    private String visitStartTime;

    private String visitEndTime;

    private String date;

    private String categoryName;
}
