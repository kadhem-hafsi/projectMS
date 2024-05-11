package com.programming.ProductMS.repository;

import com.programming.ProductMS.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
