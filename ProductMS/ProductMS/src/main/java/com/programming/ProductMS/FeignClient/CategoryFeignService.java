package com.programming.ProductMS.FeignClient;

import com.programming.ProductMS.dto.Categorydto;
//import tn.starter.shared.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(value="Category",url="http://localhost:8080")
public interface CategoryFeignService {
    @GetMapping("/category/getCategory/{id}")
    Categorydto GetCategoryById(@PathVariable("id") String id);
}
