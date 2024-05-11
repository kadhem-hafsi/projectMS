package com.programming.Category.service;

import com.google.gson.Gson;
import com.programming.Category.dto.*;
//import tn.starter.shared.dto.*;

import com.programming.Category.entity.Category;
import com.programming.Category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class CategoryService implements  ICategoryService {

    private final CategoryRepository categoryRepository;
    private final NewTopic topic;
    private final NewTopic topicDelte;
    private final NewTopic topicUpdate;
    private KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public Category CreateCategory(com.programming.Category.dto.CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .title(categoryRequest.getTitle())
                .description(categoryRequest.getDescription())
                .build();
        Category addedCategory= categoryRepository.save(category);

        Gson gson = new Gson(); // Create Gson instance
        String json = gson.toJson(addedCategory); // Serialize Stock to JSON
        kafkaTemplate.send(topic.name(), json);
        return addedCategory;
    }

    @Override
    public CategoryResponse  findCategoryById(String categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        CategoryResponse categoryResponse =CategoryResponse.builder()
                .idCategory(category.getIdCategory())
                .description(category.getDescription())
                .title(category.getTitle())
                .build();
        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> ListCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(this::mapToCategoryRespence).toList();
    }

    private CategoryResponse mapToCategoryRespence(Category category) {
        return CategoryResponse.builder()
                .idCategory(category.getIdCategory())
                .description(category.getDescription())
                .title(category.getTitle())
                .build();
    }

    @Override
    public void removeCategory(String categoryId) {
        Category category=categoryRepository.findById(categoryId).orElse(null);
        categoryRepository.deleteById(categoryId);
        Gson gson = new Gson(); // Create Gson instance
        String json = gson.toJson(category); // Serialize Stock to JSON
        kafkaTemplate.send(topicDelte.name(), json);

    }

    @Override
    public Category updateCategory(Category category) {
        Category updatedCategory= categoryRepository.save(category);
        Gson gson = new Gson(); // Create Gson instance
        String json = gson.toJson(updatedCategory); // Serialize Stock to JSON
        kafkaTemplate.send(topicUpdate.name(), json);
        return updatedCategory;
    }
}
