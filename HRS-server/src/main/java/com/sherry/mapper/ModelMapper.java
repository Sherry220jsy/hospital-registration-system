package com.sherry.mapper;

import com.github.pagehelper.Page;
import com.sherry.entity.Model;
import com.sherry.vo.ModelVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper {
    /**
     * 新增模板
     * @param model
     */
    void insert(Model model);

    /**
     * 删除模板
     * @param modelId
     */
    void delete(Long modelId);

    /**
     * 医生查询自己的所有排班模板
     * @param doctorId
     * @return
     */
    Page<ModelVO> pageGetByDoctorId(Long doctorId);

    /**
     *
     * @param doctorId
     * @return
     */
    Page<ModelVO> getByDoctorId(Long doctorId);

    /**
     * 修改排班模板
     * @param model
     */
    void update(Model model);

    /**
     * 通过排班模板id，得到排班模板
     * @param modelId
     * @return
     */
    Model getByModelId(Long modelId);

}
