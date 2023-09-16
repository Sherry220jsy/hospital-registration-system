package com.sherry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sherry.constant.StatusConstant;
import com.sherry.context.BaseContext;
import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.StatusDTO;
import com.sherry.entity.*;
import com.sherry.mapper.CategoryMapper;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.mapper.RegistrationMapper;
import com.sherry.result.PageResult;
import com.sherry.service.RegistrationService;
import com.sherry.vo.PatientRegistrationVO;
import com.sherry.vo.PatientVO;
import com.sherry.vo.RegistrationVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private ModelCategoryMapper modelCategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 患者挂号预约
     * @param registrationId
     */
    public void register(Long registrationId) {
        Registration registration=new Registration();
        registration.setRegistrationId(registrationId);
        registration.setPatientId(BaseContext.getCurrentId());
        registration.setStatus(StatusConstant.RESERVED);
        registrationMapper.update(registration);
    }

    /**
     * 删除对应模板号和日期的挂号订单
     * @param byDate
     */
    public void deleteByModelId(ByDate byDate) {
        List<ModelCategory> modelCategories = modelCategoryMapper.getByModelId(byDate.getModelId());
        if(modelCategories!=null && modelCategories.size()>0){
            for(ModelCategory modelCategory:modelCategories) {
               byDate.setModelCategoryId(modelCategory.getModelCategoryId());
                registrationMapper.deleteByModelCategoryId(byDate);
            }
        }
    }

    /**
     * 设置排班模板，增加对应的挂号订单
     * @param byDate
     */
    public void saveByModelId(ByDate byDate) {
        //modelCategory中
        List<ModelCategory> modelCategoryList = modelCategoryMapper.getByModelId(byDate.getModelId());
        //新增每个modelCategory对应挂号订单
        for(ModelCategory modelCategory:modelCategoryList){

            Registration registration = new Registration();

            BeanUtils.copyProperties(modelCategory,registration);
            //设置doctorId
            registration.setDoctorId(BaseContext.getCurrentId());
            //设置订单状态  未预约
            registration.setStatus(StatusConstant.UNAPPOINTED);
            //设置挂号订单的日期
            registration.setDate(byDate.getDate());
            //获得modelCategory对应的category，设置每个挂号的categoryId、看诊的开始时间和结束时间
            Long categoryId=modelCategory.getCategoryId();
            registration.setRegistrationId(categoryId);

            Category category=categoryMapper.getByCategoryId(categoryId);
            Integer count=modelCategory.getCount();
            Integer time=category.getTime();
            LocalTime categoryStartTime = LocalTime.parse(modelCategory.getCategoryStartTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            for (int i = 0; i < count; i++) {
                // 加上该订单预计看诊时间分钟数
                LocalTime startTime = categoryStartTime.plusMinutes(time*i);
                LocalTime endTime = categoryStartTime.plusMinutes(time*(i+1));
                registration.setVisitStartTime(startTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                registration.setVisitEndTime(endTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                //新增挂号订单
                registrationMapper.insert(registration);
            }

        }
    }


    /**
     * 查询某个医生某天的挂号信息
     * @param byDate
     * @return
     */
    public List<PatientRegistrationVO> getByDoctorId(ByDate byDate) {
        List<PatientRegistrationVO>  patientRegistrationVOs=registrationMapper.getByDoctorId(byDate);
        return patientRegistrationVOs;
    }

    /**
     * 返回患者的挂号订单信息
     * @param pageQueryDTO
     * @return
     */
    public PageResult getByPatientId(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Long patientId =BaseContext.getCurrentId();
        Page<RegistrationVO> page = registrationMapper.getByPatientId(patientId);
        long total =page.getTotal();
        List<RegistrationVO> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 医生分页查询自己挂号订单
     * @param pageQueryDTO
     * @return
     */
    public PageResult getByDoctorId(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Long doctorId =BaseContext.getCurrentId();
        Page<RegistrationVO> page = registrationMapper.pageGetByDoctorId(doctorId);
        long total =page.getTotal();
        List<RegistrationVO> records = page.getResult();
        return new PageResult(total,records);
    }

    /**
     * 医生修改挂号订单状态
     * @param statusDTO
     */
    public void updateStatus(StatusDTO statusDTO) {
        Registration registration = new Registration();
        registration.setRegistrationId(statusDTO.getRegistrationId());
        registration.setStatus(statusDTO.getStatus());
        registrationMapper.update(registration);
    }

    /**
     * 医生分页查询患者信息
     * @param pageQueryDTO
     * @return
     */
    public PageResult getPatientByDoctorId(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Long doctorId =BaseContext.getCurrentId();
        Page<PatientVO> page = registrationMapper.getPatientByDoctorId(doctorId);
        long total =page.getTotal();
        log.info("total ,{}",total);
        List<PatientVO> records = page.getResult();
        return new PageResult(total,records);
    }


}
