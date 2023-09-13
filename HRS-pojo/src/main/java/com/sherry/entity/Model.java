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
public class Model implements Serializable {
    private Long modelId;

    private Long doctorId;

    private String modelName;

    private String modelStartTime;

    private String modelEndTime;
}
