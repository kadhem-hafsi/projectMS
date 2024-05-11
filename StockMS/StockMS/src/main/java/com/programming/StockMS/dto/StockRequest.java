package com.programming.StockMS.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class StockRequest {
    private String zone;
    private String description;
}
