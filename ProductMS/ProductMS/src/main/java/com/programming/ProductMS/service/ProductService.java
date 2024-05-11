package com.programming.ProductMS.service;

import com.programming.ProductMS.FeignClient.*;
import com.programming.ProductMS.RestTemplate.RestTemplateClient;
import com.programming.ProductMS.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.ProductMS.entity.*;
import com.programming.ProductMS.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService implements  IproductService{
    private final StockFeignService stockFeignService;
    private final CategoryFeignService categoryFeignService ;
    private final ProductRepository productRepository;
    private  final StockRepository stockRepository;
    private final CategoryRepository categoryRepository;
    RestTemplateClient restTemplateClient;

    private final WebClient webClient;

    @Override
    public Product CreateProduct(RequestProduct requestProduct) {
        Product product = Product.builder()
                .libelle(requestProduct.getLibelle())
                .qte(requestProduct.getQte())
                .idStock(requestProduct.getIdStock())
                .idCategory(requestProduct.getIdCategory())
                .build();
        productRepository.save(product);
        return product;
    }

    //FeignClient
    @Override
    public ProductDto findProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        ProductDto productDto =ProductDto.builder()
                .idProduct(product.getIdProduct())
                .libelle(product.getLibelle())
                .qte(product.getQte())
                .Stockdto(stockFeignService.GetStockById(product.getIdStock()))
                .categorydto(categoryFeignService.GetCategoryById(product.getIdCategory()))
                .build();

        return productDto;
    }

    // RestTemplate
    @Override
    public ProductDto findProductByIdRestTemp(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        String id = product.getIdStock();
        String url="http://localhost:8080/stock/getstock/{id}";

        String idc = product.getIdCategory();
        String urlc="http://localhost:8080/category/getCategory/{id}";
        return ProductDto.builder()
                .idProduct(product.getIdProduct())
                .libelle(product.getLibelle())
                .qte(product.getQte())
                .Stockdto(restTemplateClient.restTemplate().getForObject(url,StockDto.class,id))
                .categorydto(restTemplateClient.restTemplate().getForObject(urlc, Categorydto.class,idc))
                .build();

    }

    // webclient
    @Override
    public ProductDto findProductByIdWebClient(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);

        StockDto stockDto = webClient.get().uri("http://localhost:8080/stock/getstock/"+product.getIdStock())
                .retrieve()
                .bodyToMono(StockDto.class)
                .block();

        Categorydto categorydto=webClient.get().uri("http://localhost:8080/category/getCategory/"+product.getIdCategory())
                .retrieve()
                .bodyToMono(Categorydto.class)
                .block();

        ProductDto productDto =ProductDto.builder()
                .idProduct(product.getIdProduct())
                .libelle(product.getLibelle())
                .qte(product.getQte())
                .categorydto(categorydto)
                .Stockdto(stockDto).build();
        return productDto;
    }

    //using kafka
    @Override
    public ProductDto findProductByIdKafka(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        Stock stock = stockRepository.findById(product.getIdStock()).orElse(null);
        Category category= categoryRepository.findById(product.getIdCategory()).orElse(null);

        StockDto stockdto= StockDto.builder()
                .idStock(stock.getIdStock())
                .description(stock.getDescription())
                .zone(stock.getZone()).build();

        Categorydto categorydto=Categorydto.builder()
                .idCategory(category.getIdCategory())
                .title(category.getTitle())
                .description(category.getDescription()).build();

        ProductDto productDto =ProductDto.builder()
                .idProduct(product.getIdProduct())
                .libelle(product.getLibelle())
                .qte(product.getQte())
                .categorydto(categorydto)
                .Stockdto(stockdto).build();

        return productDto;
    }


}
