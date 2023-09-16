package com.sherry.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sherry.constant.IsdeletedConstant;
import com.sherry.dto.ModelDTO;
import com.sherry.dto.PageModelDTO;
import com.sherry.entity.Model;
import com.sherry.entity.ModelCategory;
import com.sherry.mapper.ModelCategoryMapper;
import com.sherry.mapper.ModelMapper;
import com.sherry.result.PageResult;
import com.sherry.service.ModelService;
import com.sherry.vo.ModelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        model.setIsDeleted(IsdeletedConstant.NOTDELETED);
        modelMapper.insert(model);
        //获取insert语句生成的主键值
        Long modelId =model.getModelId();

        List<ModelCategory> modelCategories = modelDTO.getModelCategories();

        if(modelCategories!=null && modelCategories.size()>0){
            for(ModelCategory modelCategory:modelCategories) {
                modelCategory.setModelId(modelId);
                modelCategory.setIsDeleted(IsdeletedConstant.NOTDELETED);
                modelCategoryMapper.insert(modelCategory);
            }

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
     * 通过医生id，拿到所有的模板信息
     * @param doctorId
     * @return
     */
   public  List<ModelVO> getByDoctorId(Long doctorId){

       List<ModelVO> modelVOs=modelMapper.getByDoctorId(doctorId);
       if(modelVOs !=null &&modelVOs.size()>0) {
           for (ModelVO modelVO : modelVOs) {
               Long modelId = modelVO.getModelId();
               List<ModelCategory> modelCategories = modelCategoryMapper.getByModelId(modelId);
               modelVO.setModelCategories(modelCategories);
           }
       }
        return modelVOs;
   }

    /**
     * 通过医生分页查询排班模板
     * @param pageModelDTO
     * @return
     */
    public PageResult pageGetByDoctorId(PageModelDTO pageModelDTO) {
        PageHelper.startPage(pageModelDTO.getPage(),pageModelDTO.getPageSize());

        //从model表中拿到 model集合
        Page<ModelVO> page = modelMapper.getByDoctorId(pageModelDTO.getDoctorId());
        //循环，拿到每个对应的modelCategorise,添加进modelVOs集合
        List<ModelVO> modelVOs = page.getResult();
        if(modelVOs !=null &&modelVOs.size()>0){
            for (ModelVO modelVO : modelVOs) {
                Long modelId =modelVO.getModelId();
                List<ModelCategory> modelCategories =modelCategoryMapper.getByModelId(modelId);
                modelVO.setModelCategories(modelCategories);
            }
        }
        long total = page.getTotal();

        return new PageResult(total,modelVOs);
    }


    /**
     * 通过模板id得到模板
     * @param modelId
     * @return
     */
    public Model getByModelId(Long modelId) {
        Model model=modelMapper.getByModelId(modelId);
        return model;
    }

    /**
     * 修改排班模板
     * @param modelDTO
     */
    public void update(ModelDTO modelDTO) {

        //向排班模板表修改数据
        Model model = new Model();
        BeanUtils.copyProperties(modelDTO,model);
        model.setIsDeleted(IsdeletedConstant.NOTDELETED);
        modelMapper.update(model);

        List<ModelCategory> modelCategories = modelDTO.getModelCategories();

        if(modelCategories!=null && modelCategories.size()>0){
            for(ModelCategory modelCategory:modelCategories) {
                modelCategoryMapper.update(modelCategory);
            }

        }

    }


}
