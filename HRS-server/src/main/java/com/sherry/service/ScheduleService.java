package com.sherry.service;

import com.sherry.dto.SchedulePageDTO;
import com.sherry.entity.Schedule;
import com.sherry.result.PageResult;

public interface ScheduleService {

    /**
     * 新增排班信息
     * @param schedule
     */
    void save(Schedule schedule);

    /**
     * 删除排班信息通过排班id
     * @param scheduleId
     */
    void deleteByScheduleId(Long scheduleId);

    /**
     * 修改排班信息
     * @param schedule
     */
    void update(Schedule schedule);

    /**
     * 分页查询排班信息通过日期类型
     * @param schedulePageDTO
     * @return
     */
    PageResult pageByDateType(SchedulePageDTO schedulePageDTO);
}
