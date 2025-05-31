package com.qnadeel.springdemo.security.management.mapper;

import com.qnadeel.springdemo.security.management.dto.CategoryDTO;
import com.qnadeel.springdemo.security.management.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    Category CategoryDTOToCategory(CategoryDTO CategoryDTO);

    List<CategoryDTO> categoryListToCategoryDTOList(List<Category> categoryList);

    CategoryDTO categoryToCategoryDTO(Category category);


}