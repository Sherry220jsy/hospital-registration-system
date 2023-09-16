package com.sherry.dto;

import com.sherry.entity.ModelCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelDTO implements Serializable {
    private Long modelId;

    private Long doctorId;

    private String modelName;

    private String modelStartTime;

    private String modelEndTime;

    private List<ModelCategory> modelCategories;

}
