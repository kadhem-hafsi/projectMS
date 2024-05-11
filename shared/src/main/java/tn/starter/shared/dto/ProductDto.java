package tn.starter.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductDto {private long idProduct;
    private String libelle;
    private Long qte;
    private StockDto Stockdto;
    private Categorydto categorydto;

}
