package com.programming.ProductMS.service;

import com.programming.ProductMS.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.ProductMS.entity.Product;

public interface IproductService {
     Product CreateProduct(RequestProduct requestProduct);
     ProductDto findProductById(Long productId);
     ProductDto findProductByIdRestTemp(Long productId);
     ProductDto findProductByIdWebClient(Long productId);
     ProductDto findProductByIdKafka(Long productId);
}
