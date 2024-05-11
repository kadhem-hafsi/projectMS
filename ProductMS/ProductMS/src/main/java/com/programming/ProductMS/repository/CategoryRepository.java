package com.programming.ProductMS.repository;

import com.programming.ProductMS.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
