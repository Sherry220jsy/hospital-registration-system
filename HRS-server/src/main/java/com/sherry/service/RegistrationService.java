package com.sherry.service;

import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.StatusDTO;
import com.sherry.result.PageResult;
import com.sherry.vo.PatientRegistrationVO;

import java.util.List;

public interface RegistrationService {
    void register(Long registrationId, Long patientId) ;

    void deleteByModelId(Long modelId, String date);

    void saveByModelId(Long modelId, String date);


    List<PatientRegistrationVO> getByDoctorId(Long doctorId, String date);

    PageResult getByPatientId(PageQueryDTO pageQueryDTO);

    PageResult getByDoctorId(PageQueryDTO pageQueryDTO);

    void updateStatus(StatusDTO statusDTO);

    PageResult getPatientByDoctorId(PageQueryDTO pageQueryDTO);
}
