package com.sherry.controller;

import com.sherry.dto.PatientDTO;
import com.sherry.result.Result;
import com.sherry.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;


    /**
     * 注册患者账号
     * @param patientDTO
     * @return
     */
 @PostMapping("/signup")
 public Result save(@RequestBody PatientDTO patientDTO){
     log.info("注册患者账号:{}",patientDTO);
     patientService.save(patientDTO);
     return Result.success();
}




    /**
     * 患者退出
     * @return
     */
    @PostMapping("/logout")
public Result<String> logout(){
     return Result.success();
}

}
