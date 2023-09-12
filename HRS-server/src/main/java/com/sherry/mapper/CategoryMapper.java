package com.sherry.mapper;

import com.sherry.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增挂号类别
     * @param category
     */
    void insert(Category category);

    /**
     * 删除挂号类别通过挂号类别id
     * @param categoryId
     */
    void deleteById(Long categoryId);

    /**
     * 修改挂号类别信息
     * @param category
     */
    void update(Category category);

    /**
     *  查询医生的挂号类别信息
     * @param doctorId
     * @return
     */
    List<Category> getByDoctorId(Long doctorId);
}
