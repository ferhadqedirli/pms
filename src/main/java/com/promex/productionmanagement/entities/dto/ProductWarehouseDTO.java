package com.promex.productionmanagement.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductWarehouseDTO {
    private WarehouseDTO warehouse;
    private List<ProductDTO> products;
    private LocalDateTime dateTime;
}
