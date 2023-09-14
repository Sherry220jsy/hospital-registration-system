package com.sherry.service.impl;

import com.sherry.dto.ModelDTO;
import com.sherry.entity.Model;
import com.sherry.entity.ModelCategory;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.mapper.ModelMapper;
import com.sherry.service.ModelService;
import com.sherry.vo.ModelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ModelCategoryMapper modelCategoryMapper;
    /**
     * 新增排班模板
     * @param modelDTO
     */

    @Transactional
    public void save(ModelDTO modelDTO) {
        //向排班模板表插入数据
        Model model = new Model();
        BeanUtils.copyProperties(modelDTO,model);
        modelMapper.insert(model);
        //获取insert语句生成的主键值
        Long modelId =model.getModelId();

        List<ModelCategory> modelCategories = modelDTO.getModelCategories();

        if(modelCategories!=null && modelCategories.size()>0){
            modelCategories.forEach(modelCategory -> {
                modelCategory.setModelId(modelId);
            });
            //向model_category表插入n条数据
            modelCategoryMapper.insertBath(modelCategories);

        }
    }

    /**
     * 删除排班模板
     * @param modelId
     */
    @Transactional
    public void delete(Long modelId) {
        //删除对应的model_category表中的细化模板信息
        modelCategoryMapper.deleteByModelId(modelId);
        //删除model表中的模板信息
        modelMapper.delete(modelId);
    }

    /**
     * 通过医生id查询排班模板
     * @param doctorId
     * @return
     */
    public List<ModelVO> getByDoctorId(Long doctorId) {
        //从model表中拿到 model集合
        List<Model> models = modelMapper.getByDoctorId(doctorId);
        //循环，拿到每个对应的modelCategorise,添加进modelVOs集合
        List<ModelVO> modelVOs= new ArrayList<>();
        if(models !=null &&models.size()>0){
            for (Model model : models) {
                ModelVO modelVO = new ModelVO();
                BeanUtils.copyProperties(model,modelVO);
                Long modelId =model.getModelId();
                List<ModelCategory> modelCategories =modelCategoryMapper.getByModelId(modelId);
                modelVO.setModelCategories(modelCategories);
                modelVOs.add(modelVO);
            }
        }
        return modelVOs;
    }


    public Model getByModelId(Long modelId) {
        Model model=modelMapper.getByModelId(modelId);
        return model;
    }



}
