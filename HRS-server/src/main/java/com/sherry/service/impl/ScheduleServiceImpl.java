package com.sherry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sherry.constant.DateTypeConstant;
import com.sherry.dto.SchedulePageDTO;
import com.sherry.entity.Schedule;
import com.sherry.mapper.ScheduleMapper;
import com.sherry.result.PageResult;
import com.sherry.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * 新增排班信息
     * @param schedule
     */
    public void save(Schedule schedule) {
        scheduleMapper.insert(schedule);
    }

    /**
     * 删除排班信息通过排班id
     * @param scheduleId
     */
    public void deleteByScheduleId(Long scheduleId) {
        scheduleMapper.delectByScheduleId(scheduleId);
    }


    /**
     * 修改排班信息
     * @param schedule
     */
    public void update(Schedule schedule) {
        scheduleMapper.update(schedule);
    }

    /**
     * 分页查询排班信息通过日期类型
     * @param schedulePageDTO
     * @return
     */
    public PageResult pageByDateType(SchedulePageDTO schedulePageDTO) {
        //开始分页
        PageHelper.startPage(schedulePageDTO.getPage(),schedulePageDTO.getPageSize());

        Page<Schedule> page =null;

        if(schedulePageDTO.getDateType()==DateTypeConstant.DAY){
            page = scheduleMapper.getDay(schedulePageDTO);
        }else if (schedulePageDTO.getDateType()==DateTypeConstant.WEEK){
            page = scheduleMapper.getWeek(schedulePageDTO);
        }else if(schedulePageDTO.getDateType()==DateTypeConstant.MONTH){
            page = scheduleMapper.getMonth(schedulePageDTO);
        }
        long total = page.getTotal();
        List<Schedule> records = page.getResult();
        return new PageResult(total,records);
    }

}
