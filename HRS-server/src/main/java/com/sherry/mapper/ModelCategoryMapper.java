package com.sherry.mapper;

import com.sherry.entity.ModelCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelCategoryMapper {
     void deleteByModelId(Long modelId) ;

    void insertBath(List<ModelCategory> modelCategories) ;

    List<ModelCategory> getByModelId(Long modelId);
}
