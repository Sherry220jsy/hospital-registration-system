package com.sherry.mapper;

import com.github.pagehelper.Page;
import com.sherry.entity.Registration;
import com.sherry.vo.PatientRegistrationVO;
import com.sherry.vo.PatientVO;
import com.sherry.vo.RegistrationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    void deleteByModelCategoryId(Long modelCategoryId, String date);

    void insert(Registration registration);

    List<PatientRegistrationVO> getByDoctorId(Long doctorId, String date);



    void update(Registration registration);

    Page<RegistrationVO> getByPatientId(Long patientId);


    Page<RegistrationVO> pageGetByDoctorId(Long doctorId);

    Page<PatientVO>  getPatientByDoctorId(Long doctorId);
}
