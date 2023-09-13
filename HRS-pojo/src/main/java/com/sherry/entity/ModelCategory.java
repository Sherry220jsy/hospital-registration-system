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
public class ModelCategory implements Serializable {
    private Long modelCategoryId;

    private Long modelId;

    private Long categoryId;

    private String categoryStartTime;

    private String categoryEndTime;

    private Integer count;
}
