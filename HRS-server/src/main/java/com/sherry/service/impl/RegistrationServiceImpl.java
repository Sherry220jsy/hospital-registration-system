package com.sherry.service.impl;

import com.sherry.constant.StatusConstant;
import com.sherry.context.BaseContext;
import com.sherry.entity.Category;
import com.sherry.entity.ModelCategory;
import com.sherry.entity.Registration;
import com.sherry.mapper.CategoryMapper;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.mapper.RegistrationMapper;
import com.sherry.result.PageResult;
import com.sherry.service.RegistrationService;
import com.sherry.vo.PatientRegistrationVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationMapper registrationMapper;
    @Autowired
    private ModelCategoryMapper modelCategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;



    public void register(Long registrationId, Long patientId) {
        Registration registration=new Registration();
        registration.setRegistrationId(registrationId);
        registration.setPatientId(patientId);
        registration.setStatus(StatusConstant.RESERVED);
        registrationMapper.update(registration);
    }

    public void deleteByModelId(Long modelId, String date) {
        List<ModelCategory> modelCategories = modelCategoryMapper.getByModelId(modelId);
        if(modelCategories!=null && modelCategories.size()>0){
            for(ModelCategory modelCategory:modelCategories){
                registrationMapper.deleteByModelCategoryId(modelCategory.getModelCategoryId(),date);
            }
        }
    }


    public void saveByModelId(Long modelId, String date) {
        //modelCategory中
        List<ModelCategory> modelCategoryList = modelCategoryMapper.getByModelId(modelId);
        //新增每个modelCategory对应挂号订单
        for(ModelCategory modelCategory:modelCategoryList){

            Registration registration = new Registration();

            BeanUtils.copyProperties(modelCategory,registration);
            //设置doctorId
            registration.setDoctorId(BaseContext.getCurrentId());
            //设置订单状态  未预约
            registration.setStatus(StatusConstant.UNAPPOINTED);
            //设置挂号订单的日期
            registration.setDate(date);
            //获得modelCategory对应的category，设置每个挂号看诊的开始时间和结束时间
            Long categoryId=modelCategory.getCategoryId();
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

    @Override
    public List<PatientRegistrationVO> getByDoctorId(Long doctorId, String date) {
        List<PatientRegistrationVO>  patientRegistrationVOs=registrationMapper.getByDoctorId(doctorId,date);
        return patientRegistrationVOs;
    }

    /**
     *
     * @param doctorId
     * @param date
     * @return
     */

}
