package com.sherry.service;

import com.sherry.dto.ModelDTO;
import com.sherry.dto.PageModelDTO;
import com.sherry.entity.Model;
import com.sherry.result.PageResult;
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
     *分页查询排班模板
     * @param doctorId
     * @return
     */
  PageResult pageGetByDoctorId(PageModelDTO pageModelDTO);

    /**
     * 查询排班模板信息
     * @param doctorId
     * @return
     */
    List<ModelVO> getByDoctorId(Long doctorId);


    /**
     * 通过排班模板id查询排班模板
     * @param modelId
     * @return
     */
    ModelVO getByModelId(Long modelId);


    void update(ModelDTO modelDTO);


}
