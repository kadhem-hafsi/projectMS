package com.programming.StockMS.service;
import com.programming.StockMS.dto.*;

import com.programming.StockMS.entity.Stock;

import java.util.List;
//import tn.starter.shared.dto.*;
public interface IstockService {
    //void sendMesssage(Stock s);
    Stock CreateStock(StockRequest stockRequest);
    StockResponse findStockById(String stockId);

    List<StockResponse> ListStock();
    void removeStock(String stockId);

    Stock updateStock(Stock stock);

}
