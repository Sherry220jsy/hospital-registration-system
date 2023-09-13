package com.sherry.controller;

import com.sherry.context.BaseContext;
import com.sherry.dto.ModelDTO;
import com.sherry.result.Result;
import com.sherry.service.ModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    /**
     * 新增排班模板
     * @param modelDTO
     * @return
     */
    @PostMapping
    public Result save(@RequestBody ModelDTO modelDTO){
        modelDTO.setDoctorId(BaseContext.getCurrentId());
        modelService.save(modelDTO);
        return  Result.success();
    }
}
