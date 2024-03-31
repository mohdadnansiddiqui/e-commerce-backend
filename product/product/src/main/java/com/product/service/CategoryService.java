package com.product.service;

import com.product.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto);
    List<CategoryDto> findAllCategory();
    void deleteCategory(Integer id);
}
