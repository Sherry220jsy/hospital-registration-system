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
public class ModelCategoryVO implements Serializable {
    private Long modelCategoryId;

    private Long modelId;

    private String categoryStartTime;

    private String categoryEndTime;

    private String categoryName;

    private Integer count;
}
