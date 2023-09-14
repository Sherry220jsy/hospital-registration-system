package com.sherry.mapper;

import com.sherry.dto.ModelDTO;
import com.sherry.entity.Model;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ModelMapper {

    void insert(Model model);

    void delete(Long modelId);

    List<Model> getByDoctorId(Long doctorId);

    void update(Model model);

    Model getByModelId(Long modelId);

}
