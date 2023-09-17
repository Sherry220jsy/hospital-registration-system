package com.sherry.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationVO implements Serializable {
    private Long registrationId;

    private String patientName;

    private String doctorName;

    private Integer status;

    private String visitStartTime;

    private String visitEndTime;

    private String date;

    private Integer fee;

    private String categoryName;

    private  static final Integer isChange=0;
}
