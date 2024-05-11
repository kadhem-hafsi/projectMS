package com.programming.ProductMS.service;
import com.google.gson.Gson;
import com.programming.ProductMS.entity.Stock;
import com.programming.ProductMS.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@Slf4j
@AllArgsConstructor
public class StockService {
   private final StockRepository stockRepository;

    //update Stock database with added stock
    @KafkaListener(topics = "asma",groupId = "test")
    public void consumer(String msg) {
        Gson gson = new Gson();
        Stock stock = gson.fromJson(msg, Stock.class);
        stockRepository.save(stock);
       log.info(stock.toString());
    }

    //update Stock database with deleted stock
    @KafkaListener(topics = "TopicDeleStock",groupId = "del")
    public void consumerDel(String msg) {
        Gson gson = new Gson();
        Stock stock = gson.fromJson(msg, Stock.class);
        stockRepository.deleteById(stock.getIdStock());
        log.info(stock.toString());
    }

    //update Stock database with updated stock
    @KafkaListener(topics = "TopicUpdateStock",groupId = "update")
    public void consumerUpdate(String msg) {
        Gson gson = new Gson();
        Stock stock = gson.fromJson(msg, Stock.class);
        stockRepository.save(stock);
        log.info(stock.toString());
    }


}
