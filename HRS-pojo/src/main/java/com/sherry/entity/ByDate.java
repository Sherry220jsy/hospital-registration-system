package com.sherry.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ByDate {
    private  String date;
    private Long doctorId;
    private Long modelId;
private Long modelCategoryId;

}
