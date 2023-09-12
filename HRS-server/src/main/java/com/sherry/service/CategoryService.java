package com.sherry.service;

import com.sherry.entity.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增挂号类别
     * @param category
     */
    void addCategory(Category category);

    /**
     * 删除挂号类别
     * @param categoryId
     */
    void deleteCategory(Long categoryId);

    /**
     * 修改挂号类别信息
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 查询医生的挂号类别信息
     * @param doctorId
     * @return
     */
    List<Category> getByDoctorId(Long doctorId);
}
