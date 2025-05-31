package com.qnadeel.springdemo.security.management.controller;

import com.qnadeel.springdemo.security.management.dto.CategoryDTO;
import com.qnadeel.springdemo.security.management.mapper.CategoryMapper;
import com.qnadeel.springdemo.security.management.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @PostMapping("/")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDTO CategoryDTO) {
        categoryService.saveCategory(CategoryDTO);
        return ResponseEntity.ok("Add category success");
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryMapper.categoryListToCategoryDTOList(categoryService.getAllCategories()));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryMapper.categoryToCategoryDTO(categoryService.getCategoryById(categoryId)));
    }

    @GetMapping("/")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String categoryName){
        return ResponseEntity.ok(categoryMapper.categoryToCategoryDTO(categoryService.getCategoryByName(categoryName)));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO updatedCategory){
        return ResponseEntity.ok(categoryMapper.categoryToCategoryDTO(categoryService.updateCategory(categoryId,updatedCategory)));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Delete category success");
    }
}