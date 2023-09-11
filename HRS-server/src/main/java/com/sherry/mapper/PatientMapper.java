package com.sherry.mapper;

import com.sherry.entity.Patient;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PatientMapper {

    /**
     * 通过用户名查找患者
     * @param username
     * @return
     */
    Patient getByUsername(String username);

    /**
     * 添加患者信息
     * @param patient
     */
    void insert(Patient patient);
}
