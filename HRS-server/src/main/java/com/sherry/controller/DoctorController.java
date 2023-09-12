package com.sherry.controller;

import com.sherry.context.BaseContext;
import com.sherry.dto.DoctorDTO;
import com.sherry.dto.PageQueryDTO;
import com.sherry.entity.Doctor;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    /**
     * 注册医生信息
     * @param doctorDTO
     * @return
     */
    @PostMapping("/signup")
    public Result save(@RequestBody DoctorDTO doctorDTO){
        log.info("注册医生账号：{}",doctorDTO);
        doctorService.save(doctorDTO);
        return Result.success();
    }

    /**
     * 医生查询自己信息
     * @return
     */
    @GetMapping("/info")
    public Result<Doctor> getDoctor(){
        log.info(("医生查询自己信息"));
        Doctor doctor =doctorService.getById(BaseContext.getCurrentId());
        return Result.success(doctor);
    }

    /**
     * 医生修改自己的信息
     * @param doctorDTO
     * @return
     */
    @PutMapping("/info")
    public Result update(@RequestBody DoctorDTO doctorDTO){
        log.info("修改医生信息：{}",doctorDTO);
        doctorDTO.setDoctorId(BaseContext.getCurrentId());
        doctorService.update(doctorDTO);
        return Result.success();
    }

    /**
     * TODO 为完成，挂号完成后完成
     * 查询患者信息
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/patientInfo/page")
    public Result<PageResult> pagePatient(PageQueryDTO pageQueryDTO){
        log.info("患者分页查询，参数为{}",pageQueryDTO);
        PageResult pageResult = doctorService.pagePatient(pageQueryDTO);
        return Result.success(pageResult);
    }



    /**
     * 医生退出
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout(){
        return Result.success();
    }
}