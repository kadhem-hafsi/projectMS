package com.programming.StockMS.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(value = "stock")
public class Stock {
    @Id
    private String idStock;
    private String zone;
    private String description;
}
