package com.sherry.controller;


import com.sherry.context.BaseContext;
import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.PatientUpdateDTO;
import com.sherry.dto.StatusDTO;
import com.sherry.entity.ByDate;
import com.sherry.entity.Registration;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.PatientService;
import com.sherry.service.RegistrationService;
import com.sherry.vo.PatientRegistrationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private PatientService patientService;

    /**
     * 患者查询某个医生某天的挂号信息
     * @param byDate
     * @return
     */
    @GetMapping("/patient/doctor/page")
    public Result<List<PatientRegistrationVO>> getByDoctorId(ByDate byDate) {
        log.info("患者查询某个医生某天的挂号信息：{}",byDate);
       List<PatientRegistrationVO> patientRegistrationVOs = registrationService.getByDoctorId(byDate);
       return  Result.success(patientRegistrationVOs);
    }

    /**
     * 患者挂号
     * @param registrationId
     * @return
     */
    @PostMapping("/patient/doctor/page")
    public Result register(Long registrationId){
        log.info("患者挂号id：{}",registrationId);
        registrationService.register(registrationId);
        return Result.success();
    }

    /**
     * 患者分页查看历史订单
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/patient/history")
    public Result<PageResult> historyRegistration(PageQueryDTO pageQueryDTO){
        log.info("患者分页查看历史订单：{}",pageQueryDTO);
        PageResult pageResult = registrationService.getByPatientId(pageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     *患者分页查询所有医生信息
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/doctor/page")
    public Result<PageResult> registrationInfo(PageQueryDTO pageQueryDTO){
        log.info("患者分页查询所有医生信息：{}",pageQueryDTO);
        PageResult pageResult = registrationService.getByDoctorId(pageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 医生修改挂号订单状态
     * @param statusDTO
     * @return
     */
    @PutMapping("/doctor")
    public Result updateStatue(@RequestBody StatusDTO statusDTO){
        log.info("医生修改挂号订单状态：{}",statusDTO);
        registrationService.updateStatus(statusDTO);
        return Result.success();
    }

    /**
     * 医生分页查询自己的患者信息
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/doctor/patient/page")
    public Result<PageResult> patientInfo( PageQueryDTO pageQueryDTO){
        log.info("医生分页查询自己患者的信息：{}",pageQueryDTO);
        PageResult pageResult = registrationService.getPatientByDoctorId(pageQueryDTO);
        log.info("查询到的患者信息，{}",pageResult);
        return Result.success(pageResult);
    }

    /**
     * 医生修改患者信息
     * @param patientUpdateDTO
     * @return
     */
    @PutMapping("/doctor/patient/page")
    public Result updatePatientInfo(@RequestBody PatientUpdateDTO patientUpdateDTO){
        log.info("医生修改患者信息：{}",patientUpdateDTO);
        patientService.updatePatientInfo(patientUpdateDTO);
        return Result.success();
    }
}
