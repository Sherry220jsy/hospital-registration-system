package com.sherry.controller;

import com.sherry.context.BaseContext;
import com.sherry.dto.ModelDTO;
import com.sherry.entity.Doctor;
import com.sherry.entity.Model;
import com.sherry.entity.Schedule;
import com.sherry.result.Result;
import com.sherry.service.DoctorService;
import com.sherry.service.ModelService;
import com.sherry.service.RegistrationService;
import com.sherry.service.ScheduleService;
import com.sherry.vo.ModelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
        modelService.delete(modelId);
        return Result.success();
    }

    /**
     *查询排班模板
     * @return
     */
    @GetMapping
    public Result<List<ModelVO>> getByDoctorId(){
        Long doctorId=BaseContext.getCurrentId();
        List<ModelVO> modelVOs=modelService.getByDoctorId(doctorId);
        return Result.success(modelVOs);
    }

    /**
     * 修改排班模板
     * @param modelDTO
     * @return
     */
    @PutMapping
    @Transactional
    public Result update(@RequestBody ModelDTO modelDTO){
        modelDTO.setDoctorId(BaseContext.getCurrentId());
        modelService.delete(modelDTO.getModelId());
        modelService.save(modelDTO);
        return  Result.success();
    }

    /**
     * 设置排版模板
     * @param modelId
     * @return
     */
    @GetMapping("/modelId")
    @Transactional
    public Result setModel(Long modelId,String date){
        Model model=modelService.getByModelId(modelId);
        Long doctorId = BaseContext.getCurrentId();
        Schedule schedule=scheduleService.getByDate(doctorId,date);
        if (model.getModelStartTime().equals(schedule.getScheduleStartTime())&&model.getModelEndTime().equals(schedule.getScheduleEndTime())){
            //删掉原来的挂号订单
            registrationService.deleteByModelId(modelId,date);
            //新增该排班模板的挂号订单
            registrationService.saveByModelId(modelId,date);
            //修改doctor信息中的model_id
            Doctor doctor = new Doctor();
            doctor.setModelId(modelId);
            doctor.setDoctorId(doctorId);
            doctorService.update(doctor);
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
        //模板和日期排班信息要适配
        Long modelId = doctorService.getModelId(BaseContext.getCurrentId());
        Model model=modelService.getByModelId(modelId);
        Long doctorId = BaseContext.getCurrentId();
        Schedule schedule=scheduleService.getByDate(doctorId,date);
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
