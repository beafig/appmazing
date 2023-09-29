package com.campusdual.appmazing.controller;

import com.campusdual.appmazing.api.ICategoryService;
import com.campusdual.appmazing.model.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String testController(){
        return "It works";
    }

    @PostMapping(value = "/get")
    public CategoryDTO queryCategory (@RequestBody CategoryDTO categoryDTO){
        return this.categoryService.queryCategory(categoryDTO);
    }

    @GetMapping (value = "/getAll")
    public List<CategoryDTO> queryAllCategories(){
        return this.categoryService.queryAllCategories();
    }

    @PostMapping(value = "/add")
    public  int insertCategory(@RequestBody CategoryDTO categoryDTO){
        return this.categoryService.insertCategory(categoryDTO);
    }

    @PutMapping(value = "/update")
    public int updateCategory(@RequestBody CategoryDTO categoryDTO){
        return this.categoryService.updateCategory(categoryDTO);
    }

    @DeleteMapping (value = "/delete")
    public int deleteCategory(@RequestBody CategoryDTO categoryDTO){
        return this.categoryService.deleteCategory(categoryDTO);
    }
}
