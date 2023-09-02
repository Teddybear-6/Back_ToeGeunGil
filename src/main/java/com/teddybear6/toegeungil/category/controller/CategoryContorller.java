package com.teddybear6.toegeungil.category.controller;

import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryContorller {

    private final CategoryService categoryService;

    public CategoryContorller(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public ResponseEntity<?> regist(CategoryDTO categoryDTO) {


        List<Category> categoryList = categoryService.findCategoryByName(categoryDTO.getCategoryName());

        if (categoryList.size() > 0) {
            return ResponseEntity.status(404).body("중복된 카테고리 입니다");
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());

        int result = categoryService.registCategory(category);
        if (result > 0) {
            return ResponseEntity.ok().body("카테고리 등록 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("카테고리 등록 실패했습니다");
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> findAllCategory() {
        List<Category> categoryList = categoryService.findAll();

        if (categoryList.size() == 0) {
            List<String> error = new ArrayList<>();
            error.add("카테고리가 존재하지 않습니다.");

            return ResponseEntity.status(500).body(error);

        }

        List<CategoryDTO> categoryDTOS = categoryList.stream().map(m -> new CategoryDTO(m)).collect(Collectors.toList());

        return ResponseEntity.ok().body(categoryDTOS);
    }


    @GetMapping("/{categoryCode}")
    public ResponseEntity<Object> findCategoryByCode(@PathVariable int categoryCode) {
        Category category = categoryService.findCategoryByCode(categoryCode);
        if (Objects.isNull(category)) {
            return ResponseEntity.status(404).body("존재하지 않는 카테고리입니다.");
        }
        CategoryDTO categoryDTO = new CategoryDTO(category);

        return ResponseEntity.ok().body(categoryDTO);

    }




    @PutMapping("/{categoryCode}")
    public ResponseEntity<?> updateCategory(@PathVariable int categoryCode , @RequestBody CategoryDTO categoryDTO) {
        System.out.println(categoryDTO);
        Category findcategory = categoryService.findCategoryByCode(categoryCode);

        if (Objects.isNull(findcategory)) {
            return ResponseEntity.status(404).body("존재하지 않는 카테고리 입니다.");
        }

        int result = categoryService.update(findcategory, categoryDTO);

        if (result > 0) {
            return ResponseEntity.ok().body("카테고리 수정에 성공했습니다.");

        } else {
            return ResponseEntity.status(500).body("카테고리 수정에 실패했습니다.");
        }
    }


    @DeleteMapping("/{categoryCode}")
    public ResponseEntity<?> delete(@PathVariable int categoryCode) {
        Category category = categoryService.findCategoryByCode(categoryCode);

        if (Objects.isNull(category)) {
            return ResponseEntity.status(404).body("존재하지 않는 카테고리입니다.");
        }

        int result = categoryService.deleteCategory(categoryCode);

        if (result > 0) {
            return ResponseEntity.ok().body("삭제에 성공했습니다.");
        } else {
            return ResponseEntity.status(500).body("삭제에 실패했습니다.");
        }
    }


}
