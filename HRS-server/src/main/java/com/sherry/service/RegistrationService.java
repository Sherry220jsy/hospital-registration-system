package com.sherry.service;

import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.StatusDTO;
import com.sherry.entity.ByDate;
import com.sherry.result.PageResult;
import com.sherry.vo.PatientRegistrationVO;

import java.util.List;

public interface RegistrationService {
    /**
     * 患者挂号
     * @param registrationId
     * @param patientId
     */
    void register(Long registrationId) ;

    /**
     * 删除date且modelId的挂号订单
     * @param byDate
     */
    void deleteByModelId(ByDate byDate);

    /**
     * 增加date和该模板的挂号订单
     * @param byDate
     */
    void saveByModelId(ByDate byDate);

    /**
     * 通过日期和医生id获得医生的挂号信息
     * @param byDate
     * @return
     */
    List<PatientRegistrationVO> getByDoctorId(ByDate byDate);

    /**
     *
     * @param pageQueryDTO
     * @return
     */
    PageResult getByPatientId(PageQueryDTO pageQueryDTO);

    /**
     *
     * @param pageQueryDTO
     * @return
     */
    PageResult getByDoctorId(PageQueryDTO pageQueryDTO);

    /**
     *
     * @param statusDTO
     */
    void updateStatus(StatusDTO statusDTO);

    /**
     *
     * @param pageQueryDTO
     * @return
     */
    PageResult getPatientByDoctorId(PageQueryDTO pageQueryDTO);
}
