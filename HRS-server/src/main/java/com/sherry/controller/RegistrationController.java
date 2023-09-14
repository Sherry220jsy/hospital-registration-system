package com.sherry.controller;


import com.sherry.context.BaseContext;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.RegistrationService;
import com.sherry.vo.PatientRegistrationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/patient/doctor/page")
    public Result<List<PatientRegistrationVO>> getByDoctorId(Long doctorId , String date){
       List<PatientRegistrationVO> patientRegistrationVOs = registrationService.getByDoctorId(doctorId,date);
       return  Result.success(patientRegistrationVOs);
    }

    @PostMapping("/patient/doctor")
    public Result register(Long registrationId){
        Long patientId = BaseContext.getCurrentId();
        registrationService.register(registrationId,patientId);
        return Result.success();
    }
}
