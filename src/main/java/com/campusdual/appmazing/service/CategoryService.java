package com.campusdual.appmazing.service;


import com.campusdual.appmazing.api.ICategoryService;
import com.campusdual.appmazing.model.Category;
import com.campusdual.appmazing.model.dao.CategoryDAO;
import com.campusdual.appmazing.model.dto.CategoryDTO;
import com.campusdual.appmazing.model.dto.dtomapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryService")
@Lazy
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public CategoryDTO queryCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        Category categoryFinal = this.categoryDAO.getReferenceById(category.getId());
        CategoryDTO dto = CategoryMapper.INSTANCE.toDTO(categoryFinal);
        return dto;
    }

    @Override
    public List<CategoryDTO> queryAllCategories() {
        return CategoryMapper.INSTANCE.toDTOList(this.categoryDAO.findAll());
    }

    @Override
    public int insertCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        Category category1 = this.categoryDAO.saveAndFlush(category);
        return category1.getId();
    }

    @Override
    public int updateCategory(CategoryDTO categoryDTO) {
        return this.insertCategory(categoryDTO);
    }

    @Override
    public int deleteCategory(CategoryDTO categoryDTO) {
        int id = categoryDTO.getId();
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);
        categoryDAO.delete(category);
        return id;
    }
}
