package com.programming.Category.service;
import com.programming.Category.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.Category.entity.Category;

import java.util.List;

public interface ICategoryService {
    Category CreateCategory(CategoryRequest categoryRequest);

    CategoryResponse findCategoryById(String categoryId);

    List<CategoryResponse> ListCategory();

    void removeCategory(String categoryId);

    Category updateCategory(Category category);

}
