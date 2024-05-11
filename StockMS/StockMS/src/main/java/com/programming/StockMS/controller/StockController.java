package com.programming.StockMS.controller;
import com.programming.StockMS.dto.*;
//import tn.starter.shared.dto.*;
import com.programming.StockMS.entity.Stock;
import com.programming.StockMS.service.IstockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stock")
@Slf4j
public class StockController {
    private final IstockService stockService;

    //add stock and send a message to producer kafka
    // http://localhost:9099/stock/addstock
    @PostMapping("/addstock")
    @ResponseBody
    public Stock addStock(@RequestBody StockRequest stockRequest) {
        return stockService.CreateStock(stockRequest);
    }

    //get stock by id
    // http://localhost:9099/stock/getstock/662a4d08d0e425190ca1c74f
    @GetMapping("/getstock/{stockId}")
    @ResponseBody
    public StockResponse getStockById(@PathVariable("stockId") String stockId){
        return stockService.findStockById(stockId);
    }

    //get all stock
    // http://localhost:9099/stock/getAllstock
    @GetMapping("/getAllstock")
    @ResponseBody
    public List<StockResponse> getAllstock() {
        return stockService.ListStock();
    }


    //remove stok
    // http://localhost:9099/stock/RemoveStockById/662a4d08d0e425190ca1c74f
    @DeleteMapping("/RemoveStockById/{stockId}")
    public void RemoveStockById(@PathVariable("stockId") String stockId){
         stockService.removeStock(stockId);
    }

    //update Stock
    // http://localhost:9099/stock/updateStock
    @PutMapping("/updateStock")
    public Stock updateStock(@RequestBody Stock s) {
        Stock stock= stockService.updateStock(s);
        return stock;
    }
}
