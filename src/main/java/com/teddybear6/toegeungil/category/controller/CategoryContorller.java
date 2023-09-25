package com.teddybear6.toegeungil.category.controller;

import com.teddybear6.toegeungil.auth.dto.AuthUserDetail;
import com.teddybear6.toegeungil.category.dto.CategoryDTO;
import com.teddybear6.toegeungil.category.entity.Category;
import com.teddybear6.toegeungil.category.service.CategoryService;
import com.teddybear6.toegeungil.user.entity.UserEntity;
import com.teddybear6.toegeungil.user.sevice.UserViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/category")
public class CategoryContorller {

    private final CategoryService categoryService;
    private final UserViewService userViewService;

    public CategoryContorller(CategoryService categoryService, UserViewService userViewService) {
        this.categoryService = categoryService;
        this.userViewService = userViewService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> regist(@AuthenticationPrincipal AuthUserDetail userDetails,@RequestBody CategoryDTO categoryDTO) {
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

        //중복검사
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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateCategory(@AuthenticationPrincipal AuthUserDetail userDetails,@PathVariable int categoryCode, @RequestBody CategoryDTO categoryDTO) {
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> delete(@AuthenticationPrincipal AuthUserDetail userDetails, @PathVariable int categoryCode) {
        System.out.println(userDetails);
        UserEntity userEntity = userViewService.findUserEmail(userDetails.getUserEntity().getUserEmail());
        Map<String, String> respose = new HashMap<>();

        if (Objects.isNull(userEntity)) {
            respose.put("value", "관리자가 아닙니다.");
            return ResponseEntity.status(500).body(respose);
        }

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
