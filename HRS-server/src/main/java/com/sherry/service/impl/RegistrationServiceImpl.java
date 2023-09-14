package com.sherry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sherry.constant.StatusConstant;
import com.sherry.context.BaseContext;
import com.sherry.dto.PageQueryDTO;
import com.sherry.dto.StatusDTO;
import com.sherry.entity.Category;
import com.sherry.entity.ModelCategory;
import com.sherry.entity.Registration;
import com.sherry.mapper.CategoryMapper;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.mapper.RegistrationMapper;
import com.sherry.result.PageResult;
import com.sherry.result.Result;
import com.sherry.service.RegistrationService;
import com.sherry.vo.PatientRegistrationVO;
import com.sherry.vo.RegistrationVO;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    /**
     * 患者挂号预约
     * @param registrationId
     * @param patientId
     */
    public void register(Long registrationId, Long patientId) {
        Registration registration=new Registration();
        registration.setRegistrationId(registrationId);
        registration.setPatientId(patientId);
        registration.setStatus(StatusConstant.RESERVED);
        registrationMapper.update(registration);
    }

    /**
     * 删除对应模板号和日期的挂号订单
     * @param modelId
     * @param date
     */
    public void deleteByModelId(Long modelId, String date) {
        List<ModelCategory> modelCategories = modelCategoryMapper.getByModelId(modelId);
        if(modelCategories!=null && modelCategories.size()>0){
            for(ModelCategory modelCategory:modelCategories){
                registrationMapper.deleteByModelCategoryId(modelCategory.getModelCategoryId(),date);
            }
        }
    }

    /**
     * 设置排班模板，增加对应的挂号订单
     * @param modelId
     * @param date
     */
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


    public List<PatientRegistrationVO> getByDoctorId(Long doctorId, String date) {
        List<PatientRegistrationVO>  patientRegistrationVOs=registrationMapper.getByDoctorId(doctorId,date);
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
     * 医生查询挂号订单
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


}
