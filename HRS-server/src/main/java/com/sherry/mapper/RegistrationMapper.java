package com.sherry.mapper;

import com.github.pagehelper.Page;
import com.sherry.entity.ByDate;
import com.sherry.entity.Registration;
import com.sherry.vo.PatientRegistrationVO;
import com.sherry.vo.PatientVO;
import com.sherry.vo.RegistrationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegistrationMapper {
    /**
     * 删除通过date和modelCategoryId
     * @param byDate
     */
    void deleteByModelCategoryId(ByDate byDate);

    /**
     *添加挂号订单
     * @param registration
     */
    void insert(Registration registration);

    /**
     *查询某个医生某天的挂号信息
     * @param byDate
     * @return
     */
    List<PatientRegistrationVO> getByDoctorId(ByDate byDate);

    /**
     * 修改挂号订单信息
     * @param registration
     */
    void update(Registration registration);

    /**
     * 患者分页查看历史订单
     * @param patientId
     * @return
     */
    Page<RegistrationVO> getByPatientId(Long patientId);

    /**
     * 医生分页查询自己挂号订单
     * @param doctorId
     * @return
     */
    Page<RegistrationVO> pageGetByDoctorId(Long doctorId);

    /**
     * 医生分页查询患者信息
     * @param doctorId
     * @return
     */
    Page<PatientVO>  getPatientByDoctorId(Long doctorId);
}
