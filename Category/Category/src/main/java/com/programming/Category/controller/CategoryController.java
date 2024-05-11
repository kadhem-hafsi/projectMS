package com.programming.Category.controller;

import com.programming.Category.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.Category.entity.Category;
import com.programming.Category.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    private final ICategoryService categoryService;

    //add Category and send a message to producer kafka
    // http://localhost:6066/category/addCategory
    @PostMapping("/addCategory")
    @ResponseBody
    public Category addStock(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.CreateCategory(categoryRequest);
    }

    //get Category by id
    // http://localhost:6066/category/getCategory/4444444
    @GetMapping("/getCategory/{CategoryId}")
    @ResponseBody
    public CategoryResponse getCategoryById(@PathVariable("CategoryId") String CategoryId){
        return categoryService.findCategoryById(CategoryId);
    }

    //update Category
    // http://localhost:6066/category/updateCategory
    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestBody Category c) {
        Category category= categoryService.updateCategory(c);
        return category;
    }

    //get all Category
    // http://localhost:6066/category/getAllCategory
    @GetMapping("/getAllCategory")
    @ResponseBody
    public List<CategoryResponse> getAllCategory() {
        return categoryService.ListCategory();
    }

    //remove catogry
    // http://localhost:6066/category/RemoveCategoryById/662a4d08d0e425190ca1c74f
    @DeleteMapping("/RemoveCategoryById/{CategoryId}")
    public void RemoveCategoryById(@PathVariable("CategoryId") String CategoryId){
        categoryService.removeCategory(CategoryId);
    }



}
