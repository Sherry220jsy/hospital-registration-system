package com.sherry.controller;

import com.sherry.context.BaseContext;
import com.sherry.dto.SchedulePageDTO;
import com.sherry.entity.Schedule;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.PortUnreachableException;


@Slf4j
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    /**
     * 新增排班信息
     * @param schedule
     * @return
     */
    @PostMapping("/info")
    public Result save(@RequestBody Schedule schedule){
        log.info("新增排班信息：{}",schedule);
        schedule.setDoctorId(BaseContext.getCurrentId());
        scheduleService.save(schedule);
        return Result.success();
    }

    /**
     * 删除排班信息通过排班id
     * @param scheduleId
     * @return
     */
    @DeleteMapping("/info")
    public Result delete(Long scheduleId){
        log.info("删除排班信息通过排班id：{}",scheduleId);
        scheduleService.deleteByScheduleId(scheduleId);
        return Result.success();
    }

    /**
     * 修改排班信息
     * @param schedule
     * @return
     */
    @PutMapping("/info")
    public Result update(@RequestBody Schedule schedule){
        log.info("修改排班信息：{}",schedule);
        scheduleService.update(schedule);
        return  Result.success();
    }

    /**
     * 分页查询排班信息通过日期类型
     * @param schedulePageDTO
     * @return
     */
    @GetMapping("/info/page")
    public Result<PageResult> pageByDateType(SchedulePageDTO schedulePageDTO){
        log.info("分页查询排班信息通过日期类型：{}",schedulePageDTO);
        PageResult pageResult = scheduleService.pageByDateType(schedulePageDTO);
        return Result.success(pageResult);
    }

}
