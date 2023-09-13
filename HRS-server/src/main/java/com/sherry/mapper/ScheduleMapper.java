package com.sherry.mapper;

import com.github.pagehelper.Page;
import com.sherry.dto.SchedulePageDTO;
import com.sherry.entity.Schedule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleMapper {


    /**
     * 新增排班信息
     * @param schedule
     */
    void insert(Schedule schedule);

    /**
     * 删除排班信息通过排班id
     * @param scheduleId
     */
    void delectByScheduleId(Long scheduleId);

    /**
     * 修改排班信息
     * @param schedule
     */
    void update(Schedule schedule);

    /**
     * 查询某天排班信息
     * @param schedulePageDTO
     * @return
     */
    Page<Schedule> getDay(SchedulePageDTO schedulePageDTO);

    /**
     * 查询某周排班信息
     * @param schedulePageDTO
     * @return
     */
    Page<Schedule> getWeek(SchedulePageDTO schedulePageDTO);

    /**
     * 查询某月排班信息
     * @param schedulePageDTO
     * @return
     */
    Page<Schedule> getMonth(SchedulePageDTO schedulePageDTO);
}
