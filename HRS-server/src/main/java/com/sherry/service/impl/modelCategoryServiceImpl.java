package com.sherry.service.impl;

import com.sherry.entity.ModelCategory;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.service.ModelCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class modelCategoryServiceImpl implements ModelCategoryService {
    @Autowired
    private ModelCategoryMapper modelCategoryMapper;


    /**
     * 得到modelCategory通过modelCategoreId
     * @param modelCategoreId
     * @return
     */
    public ModelCategory getById(Long modelCategoreId) {
        ModelCategory modelCategory=modelCategoryMapper.getById(modelCategoreId);
        return modelCategory;
    }
}
