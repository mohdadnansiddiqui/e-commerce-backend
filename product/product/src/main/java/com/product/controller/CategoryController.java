package com.product.controller;

import com.product.dto.CategoryDto;
import com.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/category/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("create")
    private ResponseEntity<CategoryDto> createCategory( @RequestBody @Valid CategoryDto categoryDto){

        return new ResponseEntity<>(categoryService.createCategory(categoryDto),
               HttpStatus.CREATED);
    }
    @PutMapping("update")
   private ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto),
                HttpStatus.OK);
    }
    @GetMapping
   private ResponseEntity<List<CategoryDto>> getAllCategory(){
        return  new ResponseEntity<>(categoryService.findAllCategory(),HttpStatus.OK);
    }



}
