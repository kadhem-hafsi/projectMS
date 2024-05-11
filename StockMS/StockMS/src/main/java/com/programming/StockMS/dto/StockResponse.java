package com.programming.StockMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class StockResponse {
    private String idStock;
    private String zone;
    private String description;
}
