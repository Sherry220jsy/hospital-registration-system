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
public class Schedule implements Serializable {

    private Long scheduleId;

    private Long doctorId;

    private String date;

    private String scheduleStartTime;

    private String scheduleEndTime;
}
