package com.sherry.mapper;

import com.sherry.entity.ModelCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelCategoryMapper {
    /**
     * 删除排班模板中的细化类别模板
     * @param modelId
     */
     void deleteByModelId(Long modelId) ;

    /**
     * 插入多条细化类别模板
     * @param modelCategory
     */
    void insert(ModelCategory modelCategory) ;

    /**
     * 查询模板id对应的所有细化类别模板
     * @param modelId
     * @return
     */
    List<ModelCategory> getByModelId(Long modelId);

    /**
     * 修改模板id对应的细化类别模板
     * @param modelCategory
     */
    void update(ModelCategory modelCategory);
}
