package com.teddybear6.toegeungil.category.dto;

import com.teddybear6.toegeungil.category.entity.Category;

public class CategoryDTO {

    private int categoryCode;

    private String categoryName;


    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        this.categoryCode = category.getCategoryCode();
        this.categoryName = category.getCategoryName();
    }
    public CategoryDTO(int categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }



    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
