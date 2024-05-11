package tn.starter.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RequestProduct {
    private String libelle;
    private Long qte;
    private String idStock;
    private String idCategory;
}
