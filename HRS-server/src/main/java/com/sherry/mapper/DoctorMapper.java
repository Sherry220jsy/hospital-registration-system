package com.sherry.mapper;

import com.sherry.entity.Doctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
     * @param id
     * @return
     */
    Doctor getById(Long id);

    /**
     * 更新医生信息
     * @param doctor
     */
    void update(Doctor doctor);
}
