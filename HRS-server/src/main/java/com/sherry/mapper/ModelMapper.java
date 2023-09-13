package com.sherry.mapper;

import com.sherry.dto.ModelDTO;
import com.sherry.entity.Model;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ModelMapper {

    void insert(Model model);
}
