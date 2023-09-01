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
    public int registCategory(String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);

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
        Category category = new Category(findcategory.getCategoryCode(), categoryDTO.getCategoryName());
        Category result = categoryRepository.save(category);

        if(Objects.isNull(result)){
            return 0;
        }else {
            return 1;
        }
    }
}
