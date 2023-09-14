package com.sherry.mapper;

import com.sherry.entity.Registration;
import com.sherry.vo.PatientRegistrationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    void deleteByModelCategoryId(Long modelCategoryId, String date);

    void insert(Registration registration);

    List<PatientRegistrationVO> getByDoctorId(Long doctorId, String date);



    void update(Registration registration);
}
