package com.sherry.service;

import com.sherry.entity.Model;
import com.sherry.entity.ModelCategory;

public interface ModelCategoryService {

    /**
     * 得到modelCategory通过modelCategoreId
     * @param modelCategoreId
     * @return
     */
    ModelCategory getById(Long modelCategoreId);

}
