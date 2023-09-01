package com.teddybear6.toegeungil.category.controller;

import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryContorller {

    private final CategoryService categoryService;

    public CategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<?> regist(String categoryName){
        System.out.println(categoryName);
        Category category = new Category();
        category.setCategoryName(categoryName);
        int result = categoryService.registCategory(categoryName);
        if(result>0){
            return ResponseEntity.ok().body("카테고리 등록 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("카테고리 등록 실패했습니다");
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> findAllCategory(){
        List<Category> categoryList = categoryService.findAll();
        if(Objects.isNull(categoryList)){
            ResponseEntity.status(500).body("카테고리가 존재하지 않습니다.");
        }

        List<CategoryDTO> categoryDTOS  = categoryList.stream().map(m-> new CategoryDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTOS);
    }


    @GetMapping("/{categoryCode}")
    public ResponseEntity<Object> findCategoryByCode(@PathVariable int categoryCode){
        Category category = categoryService.findCategoryByCode(categoryCode);
        if(Objects.isNull(category)){
            return ResponseEntity.status(404).body("존재하지 않는 카테고리입니다.");
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);

        return ResponseEntity.ok().body(categoryDTO);

    }


    @PutMapping
    public ResponseEntity<?> updateCategory(CategoryDTO categoryDTO){
        System.out.println(categoryDTO);
        Category findcategory = categoryService.findCategoryByCode(categoryDTO.getCategoryCode());

        if(Objects.isNull(findcategory)){
            return ResponseEntity.status(404).body("존재하지 않는 카테고리 입니다.");
        }

        int result = categoryService.update(findcategory,categoryDTO);

        if(result>0){
            return ResponseEntity.ok().body("카테고리 수정에 성공했습니다.");

        }else {
            return ResponseEntity.status(500).body("카테고리 수정에 실패했습니다.");
        }
    }


    @DeleteMapping


}
