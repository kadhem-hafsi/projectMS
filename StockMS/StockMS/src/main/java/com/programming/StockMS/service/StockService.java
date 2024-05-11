package com.programming.StockMS.service;
import com.google.gson.Gson;
import com.programming.StockMS.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.StockMS.entity.Stock;
import com.programming.StockMS.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockService implements IstockService{
   private final StockRepository stockRepository;
   private final NewTopic topic;
   private final NewTopic topicDelte;
   private final NewTopic topicUpdate;
   private KafkaTemplate<String,String> kafkaTemplate;



    // add a stock and send message tp the kafka producer
    @Override
    public Stock CreateStock(StockRequest stockRequest) {
        Stock stock = Stock.builder()
                .zone(stockRequest.getZone())
                .description(stockRequest.getDescription())
                .build();
          Stock addedstock= stockRepository.save(stock);

           Gson gson = new Gson(); // Create Gson instance
           String json = gson.toJson(addedstock); // Serialize Stock to JSON
           kafkaTemplate.send(topic.name(), json);
        return addedstock;
    }

    //remove stock and send a message to kafka producer
    @Override
    public void removeStock(String stockId) {
        Stock stock=stockRepository.findById(stockId).orElse(null);
        stockRepository.deleteById(stockId);
        Gson gson = new Gson(); // Create Gson instance
        String json = gson.toJson(stock); // Serialize Stock to JSON
        kafkaTemplate.send(topicDelte.name(), json);
    }

    //update stock and send a message to kafka producer
    @Override
    public Stock updateStock(Stock stock) {

        Stock updatedStock= stockRepository.save(stock);
        Gson gson = new Gson(); // Create Gson instance
        String json = gson.toJson(updatedStock); // Serialize Stock to JSON
        kafkaTemplate.send(topicUpdate.name(), json);
        return updatedStock;
    }

    //findStockById
    @Override
    public StockResponse findStockById(String stockId) {
        Stock stock=stockRepository.findById(stockId).orElse(null);
        StockResponse stockResponse =StockResponse.builder()
                .idStock(stock.getIdStock())
                .description(stock.getDescription())
                .zone(stock.getZone())
                .build();
        return stockResponse;
    }

    //ListStock
    @Override
    public List<StockResponse> ListStock() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(this::mapToStockRespence).toList();
    }

    private StockResponse mapToStockRespence(Stock stock) {
        return StockResponse.builder()
                .idStock(stock.getIdStock())
                .description(stock.getDescription())
                .zone(stock.getZone())
                .build();
    }


}
