package com.qnadeel.springdemo.security.management.service;

import com.qnadeel.springdemo.security.management.dto.CategoryDTO;
import com.qnadeel.springdemo.security.management.entities.Category;
import com.qnadeel.springdemo.security.management.exeption.ResourcesNotFoundException;
import com.qnadeel.springdemo.security.management.mapper.CategoryMapper;
import com.qnadeel.springdemo.security.management.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public void saveCategory(CategoryDTO CategoryDTO) {

        if (categoryRepository.existsByCategoryName(CategoryDTO.getCategoryName())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exists");
        }
        categoryRepository.save(categoryMapper.CategoryDTOToCategory(CategoryDTO));
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new ResourcesNotFoundException("Category not found"));
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new ResourcesNotFoundException("Category not found"));
    }

    public Category updateCategory(Long categoryId, CategoryDTO updatedCategory) {
        Category category = getCategoryById(categoryId);
        category.setCategoryName(updatedCategory.getCategoryName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}