package com.sherry.service.impl;

import com.sherry.dto.ModelDTO;
import com.sherry.entity.Model;
import com.sherry.mapper.ModelMapper;
import com.sherry.service.ModelService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelMapper modelMapper;
    /**
     * 新增排班模板
     * @param modelDTO
     */
    public void save(ModelDTO modelDTO) {
        //向排班模板表插入数据
        Model model = new Model();
        BeanUtils.copyProperties(modelDTO,model);
        modelMapper.insert(model);
        //获取insert语句生成的
    }
}
