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
    @Select("select * from doctor where username = #{username}")
    Doctor getByUsername(String username);
}
