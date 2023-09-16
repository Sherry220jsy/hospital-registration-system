package com.sherry.controller;

import com.sherry.constant.StatusConstant;
import com.sherry.context.BaseContext;
import com.sherry.dto.ModelDTO;
import com.sherry.dto.ModelDateDTO;
import com.sherry.dto.PageModelDTO;
import com.sherry.entity.ByDate;
import com.sherry.entity.Doctor;
import com.sherry.entity.Model;
import com.sherry.entity.Schedule;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.DoctorService;
import com.sherry.service.ModelService;
import com.sherry.service.RegistrationService;
import com.sherry.service.ScheduleService;
import com.sherry.vo.ModelVO;
import com.sherry.vo.PatientRegistrationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private DoctorService doctorService;

    /**
     * 新增排班模板
     * @param modelDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody ModelDTO modelDTO){
        log.info("增加排班模板：{}",modelDTO);
        modelDTO.setDoctorId(BaseContext.getCurrentId());
        modelService.save(modelDTO);
        return  Result.success();
    }

    /**
     * 删除排班模板
     * @param modelId
     * @return
     */
    @DeleteMapping
    public Result delect(Long modelId){
        log.info("删除排班模板：{}",modelId);
        modelService.delete(modelId);
        return Result.success();
    }

    /**
     *查询排班模板
     * @return
     */
    @GetMapping
    public Result<PageResult> getByDoctorId(PageModelDTO pageModelDTO){
        log.info("查询排班模板：{}",pageModelDTO);
       pageModelDTO.setDoctorId(BaseContext.getCurrentId());
        PageResult pageResult=modelService.pageGetByDoctorId(pageModelDTO);
        return Result.success(pageResult);
    }

    /**
     * 修改排班模板
     * @param modelDTO
     * @return
     */
    @PutMapping
    public Result update(@RequestBody ModelDTO modelDTO){
        log.info("修改排班模板：{}",modelDTO);
        modelDTO.setDoctorId(BaseContext.getCurrentId());
        modelService.update(modelDTO);
        return  Result.success();
    }

    /**
     * 设置排版模板
     * @param byDate
     * @return
     */
    @GetMapping("/modelId")
    @Transactional
    public Result setModel(ByDate byDate){

        log.info("设置排班模板的日期和模板id{}",byDate);
        //得到date的排班信息和模板信息
        Model model=modelService.getByModelId(byDate.getModelId());
        Long doctorId = BaseContext.getCurrentId();
        byDate.setDoctorId(doctorId);
        Schedule schedule=scheduleService.getByDate(byDate);
        LocalDate localDate = LocalDate.parse(byDate.getDate());
        // 获取今天的日期
        LocalDate today = LocalDate.now();
        //date大于等于今天的日期的排班
        if (localDate.isBefore(today)){
            return Result.error("不能设置之前的日期");
        }

        //排班起始时间和结束时间需要符合模板
        if (model.getModelStartTime().equals(schedule.getScheduleStartTime())&&model.getModelEndTime().equals(schedule.getScheduleEndTime())){

            //如果有患者已经挂号当日的号 不可以修改设置的当日的排班模板
            List<PatientRegistrationVO> patientRegistrationVOs = registrationService.getByDoctorId(byDate);
            for(PatientRegistrationVO patientRegistration :patientRegistrationVOs){
                if (!patientRegistration.getStatus().equals(StatusConstant.UNAPPOINTED) ){
                    return Result.error("当日已有患者挂号，不能修改设置当日排班模板");
                }
            }
            //删掉原来的挂号订单
            registrationService.deleteByModelId(byDate);
            //新增该排班模板的挂号订单
            registrationService.saveByModelId(byDate);
            //如果日期为当日就修改doctor信息中的model_id
            if (localDate.equals(today)){
                Doctor doctor = new Doctor();
                doctor.setModelId(byDate.getModelId());
                doctor.setDoctorId(doctorId);
                doctorService.update(doctor);
            }
            return Result.success();
        }else{
            return Result.error("该排班模板不适用当日排班");
        }



    }

    /**
     * 排班
     * @param date
     * @return
     */
    @GetMapping("/doctor")
    public Result<ModelVO> getModel(String date){
        log.info("排班展示日期：{}",date);
        //模板和日期排班信息要适配
        Long modelId = doctorService.getModelId(BaseContext.getCurrentId());
        Model model=modelService.getByModelId(modelId);
        Long doctorId = BaseContext.getCurrentId();
        ByDate byDate=new ByDate();
        byDate.setModelId(modelId);
        byDate.setDate(date);
        byDate.setDoctorId(doctorId);
        Schedule schedule=scheduleService.getByDate(byDate);
        if (model.getModelStartTime().equals(schedule.getScheduleStartTime())&&model.getModelEndTime().equals(schedule.getScheduleEndTime())){
            List<ModelVO> modelVOs=modelService.getByDoctorId(doctorId);
            for(ModelVO modelVO:modelVOs){
                if (modelVO.getModelId().equals(modelId)){
                    return Result.success(modelVO);
                }
            }
        }
        return Result.error("请先设置今日合适排班模板");
    }
}
