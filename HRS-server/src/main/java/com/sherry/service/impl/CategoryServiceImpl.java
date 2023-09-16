package com.sherry.service.impl;

import com.sherry.constant.IsdeletedConstant;
import com.sherry.entity.Category;
import com.sherry.mapper.CategoryMapper;
import com.sherry.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    /**
     * 新增挂号类别
     * @param category
     */
    public void addCategory(Category category) {
        category.setIsDeleted(IsdeletedConstant.NOTDELETED);
        categoryMapper.insert(category);
    }

    /**
     * 删除挂号类别
     * @param categoryId
     */
    public void deleteCategory(Long categoryId) {
        categoryMapper.deleteById(categoryId);
    }

    /**
     *修改挂号类别信息
     * @param category
     */
    public void updateCategory(Category category) {
        categoryMapper.update(category);
    }

    /**
     * 查询医生的挂号类别信息
     * @param doctorId
     * @return
     */
    public List<Category> getByDoctorId(Long doctorId) {
        List<Category>  categories=categoryMapper.getByDoctorId(doctorId);
        return categories;
    }
}
