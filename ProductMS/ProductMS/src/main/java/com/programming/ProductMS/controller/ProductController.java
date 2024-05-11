package com.programming.ProductMS.controller;

import com.programming.ProductMS.dto.*;
import com.programming.ProductMS.entity.Product;
//import tn.starter.shared.dto.*;
import com.programming.ProductMS.service.IproductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
//@RequestMapping("ProductMS/product")
@RequestMapping("/product")
public class ProductController {
    private final IproductService productService;


    //add product
    // http://localhost:8088/product/addProduct
    @PostMapping("/addProduct")
    @ResponseBody
    public Product addProduct(@RequestBody RequestProduct requestProduct){
        return productService.CreateProduct(requestProduct);
    }

    //FeingnClient
    // http://localhost:8088/product/getProductById/1
    @GetMapping("/getProductById/{productId}")
    @ResponseBody
    public ProductDto getProductById(@PathVariable("productId") Long productId){
        return productService.findProductById(productId);
    }

    //RestTemplate
    // http://localhost:8088/product/getProductByIdTemp/1
    @GetMapping("/getProductByIdTemp/{id}")
    @ResponseBody
    public ProductDto getProductByIdTemp(@PathVariable("id") Long id){
        return productService.findProductByIdRestTemp(id);
    }


    // Webclient
    // http://localhost:8088/product/getProductByWebClient/1
    @GetMapping("/getProductByWebClient/{id}")
    @ResponseBody
    public ProductDto getProductByWebClient(@PathVariable("id") Long id){
        return productService.findProductByIdWebClient(id);
    }

    // kafka
    // http://localhost:8088/product/getProductByKafka/1
    @GetMapping("/getProductByKafka/{id}")
    @ResponseBody
    public ProductDto getProductByKafka(@PathVariable("id") Long id){
        return productService.findProductByIdKafka(id);
    }



}
