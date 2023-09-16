package com.sherry.mapper;

import com.github.pagehelper.Page;
import com.sherry.dto.PatientDoctorVO;
import com.sherry.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorMapper {
    /**
     * 通过用户名查找医生
     * @param username
     * @return
     */
    Doctor getByUsername(String username);

    /**
     * 添加医生信息
     * @param doctor
     */
    void insert(Doctor doctor);

    /**
     * 用id查询医生信息
     * @param doctorId
     * @return
     */
    Doctor getById(Long doctorId);

    /**
     * 更新医生信息
     * @param doctor
     */
    void update(Doctor doctor);


    /**
     * 根据医生id查询他的模板id
     * @param doctorId
     * @return
     */
    Long getModelId(Long doctorId);

    /**
     *患者分页查询医生信息
     * @return
     */
    Page<PatientDoctorVO> getPatientDoctor();
}
