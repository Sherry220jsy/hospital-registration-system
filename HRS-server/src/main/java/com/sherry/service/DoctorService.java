package com.sherry.service;

import com.sherry.dto.DoctorDTO;
import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Category;
import com.sherry.entity.Doctor;
import com.sherry.result.PageResult;

public interface DoctorService {

    /**
     * 医生登录
     * @param userLoginDTO
     * @return
     */
    Doctor login(UserLoginDTO userLoginDTO);

    /**
     * 注册医生信息
     * @param doctorDTO
     */
    void save(DoctorDTO doctorDTO);

    /**
     * 根据id查询医生
     * @param id
     * @return
     */
    Doctor getById(Long id);


    /**
     * 修改医生信息
     * @param doctorDTO
     */
    void update(Doctor doctor);

    /**
     * 查询患者信息
     * @param pageQueryDTO
     * @return
     */
    PageResult pagePatient(PageQueryDTO pageQueryDTO);


    Long getModelId(Long currentId);

    /**
     * 分页查询所有医生
     * @return
     */
    PageResult pageDoctor(PageQueryDTO pageQueryDTO);


}
