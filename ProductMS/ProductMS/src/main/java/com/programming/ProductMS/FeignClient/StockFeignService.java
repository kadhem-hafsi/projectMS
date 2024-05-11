package com.programming.ProductMS.FeignClient;

import com.programming.ProductMS.dto.StockDto;
//import tn.starter.shared.dto.*;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="StockMS",url="http://localhost:8080")
public interface StockFeignService {
   @GetMapping("/stock/getstock/{id}")
    StockDto GetStockById(@PathVariable("id") String id);
}
