package com.teddybear6.toegeungil.category.service;


import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    @Transactional
    public int registCategory(Category category) {

        Category findCategory = categoryRepository.save(category);

        if (Objects.isNull(findCategory)) {
            return 0;
        } else {
            return 1;
        }


    }

    public Category findCategoryByCode(int categoryCode) {
        Category category = categoryRepository.findById(categoryCode);
        return category;
    }

    @Transactional
    public int update(Category findcategory, CategoryDTO categoryDTO) {
       findcategory.setCategoryName(categoryDTO.getCategoryName());
        Category result = categoryRepository.save(findcategory);

        if (result.getCategoryName().equals(categoryDTO.getCategoryName())) {
            return 1;
        } else {
            return 0;
        }
    }

    @Transactional
    public int deleteCategory(int categoryCode) {
        categoryRepository.deleteById(categoryCode);
        Category category = categoryRepository.findById(categoryCode);

        if (Objects.isNull(category)) {
            return 1;
        } else {
            return 2;
        }
    }

    public List<Category> findCategoryByName(String categoryName) {
        List<Category> categoryList = categoryRepository.findBycategoryName(categoryName);

        return categoryList;
    }

}
