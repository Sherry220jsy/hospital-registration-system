package com.sherry.service;

import com.sherry.dto.ModelDTO;

public interface ModelService {

    /**
     * 新增排班模板
     * @param modelDTO
     */
    void save(ModelDTO modelDTO);
}
