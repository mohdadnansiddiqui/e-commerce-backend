package com.product.service.impl;

import com.product.dto.CategoryDto;
import com.product.entity.Category;
import com.product.exception.custom.NotFoundException;
import com.product.repository.CategoryRepository;
import com.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper modelMapper;
    private  final CategoryRepository categoryRepository;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
       Category category= modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category= modelMapper.map(categoryDto, Category.class);
        return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        List<Category> categories=categoryRepository.findAll();
        return categories.stream().map(c->modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer id) {
        Category category=categoryRepository.findById(id).orElseThrow(()-> new NotFoundException("given id "+id+" not present"));
        categoryRepository.delete(category);
    }
}
