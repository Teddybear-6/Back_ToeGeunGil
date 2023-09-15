package com.teddybear6.toegeungil.category.repository;

import com.teddybear6.toegeungil.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findById(int categoryCode);
    List<Category> findBycategoryName(String categoryName);

}
