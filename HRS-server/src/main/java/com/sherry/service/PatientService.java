package com.sherry.service;


import com.sherry.dto.PatientDTO;
import com.sherry.dto.PatientUpdateDTO;
import com.sherry.dto.UserLoginDTO;
import com.sherry.entity.Patient;

public interface PatientService {
    /**
     * 患者登录
     * @param userLoginDTO
     * @return
     */
    Patient login(UserLoginDTO userLoginDTO);

    /**
     * 注册患者账号
     * @param patientDTO
     */
    void save(PatientDTO patientDTO);

    /**
     * 医生修改患者信息
     * @param patientUpdateDTO
     */
    void updatePatientInfo(PatientUpdateDTO patientUpdateDTO);
}
