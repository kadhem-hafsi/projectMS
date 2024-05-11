package com.programming.ProductMS.repository;

import com.programming.ProductMS.entity.Product;
import com.programming.ProductMS.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
