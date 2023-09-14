package com.sherry.service;

import com.sherry.dto.ModelDTO;
import com.sherry.entity.Model;
import com.sherry.vo.ModelVO;

import java.util.List;

public interface ModelService {

    /**
     * 新增排班模板
     * @param modelDTO
     */
    void save(ModelDTO modelDTO);

    /**
     * 删除模板
     * @param modelId
     */
    void delete(Long modelId);

    /**
     *
     * @param doctorId
     * @return
     */
    List<ModelVO> getByDoctorId(Long doctorId);

    Model getByModelId(Long modelId);

    /**
     *
     * @param modelDTO
     */
//    void update(ModelDTO modelDTO);
}
