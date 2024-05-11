package com.programming.ProductMS.service;

import com.google.gson.Gson;
import com.programming.ProductMS.entity.Category;
import com.programming.ProductMS.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //update Stock database with added stock
    @KafkaListener(topics = "categoryKafka",groupId = "addCat")
    public void consumer(String msg) {
        Gson gson = new Gson();
        Category category = gson.fromJson(msg, Category.class);
        categoryRepository.save(category);
        log.info(category.toString());
    }

    //update Stock database with deleted category
    @KafkaListener(topics = "TopicDeleCategory",groupId = "delcat")
    public void consumerDel(String msg) {
        Gson gson = new Gson();
        Category category = gson.fromJson(msg, Category.class);
        categoryRepository.deleteById(category.getIdCategory());
        log.info(category.toString());
    }

    //update Stock database with updated category
    @KafkaListener(topics = "TopicUpdateCategory",groupId = "updatecat")
    public void consumerUpdate(String msg) {
        Gson gson = new Gson();
        Category category = gson.fromJson(msg, Category.class);
        categoryRepository.save(category);
        log.info(category.toString());
    }





}
