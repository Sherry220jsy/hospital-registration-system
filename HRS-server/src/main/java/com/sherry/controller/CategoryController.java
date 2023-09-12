package com.sherry.controller;

import com.sherry.context.BaseContext;
import com.sherry.entity.Category;
import com.sherry.result.Result;
import com.sherry.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

@Autowired
private CategoryService categoryService;
    /**
     * 新增挂号类别
     * @param category
     * @return
     */
    @PostMapping()
    public Result addCategory(@RequestBody Category category){
        log.info("新增挂号类别:{}",category);
        category.setDoctorId(BaseContext.getCurrentId());
        categoryService.addCategory(category);
        return Result.success();
    }

    /**
     * 删除挂号类别
     * @param categoryId
     * @return
     */
    @DeleteMapping()
    public Result deleteCategory(Long categoryId){
        log.info("删除挂号类别:{}",categoryId);
        categoryService.deleteCategory(categoryId);
        return  Result.success();
    }

    /**
     * 修改挂号类别信息
     * @param category
     * @return
     */
    @PutMapping()
    public Result updateCategory(@RequestBody Category category){
        log.info("修改挂号类别信息:{}",category);
        category.setDoctorId(BaseContext.getCurrentId());
        categoryService.updateCategory(category);
        return Result.success();
    }

    /**
     * 查询医生的挂号类别信息
     * @return
     */
    @GetMapping
    public Result<List<Category>> getCategory(){
        Long doctorId = BaseContext.getCurrentId();
        log.info("查询医生 ：{} 的挂号类别信息",doctorId);
        List<Category> categories = categoryService.getByDoctorId(doctorId);
        return  Result.success(categories);
    }
}
