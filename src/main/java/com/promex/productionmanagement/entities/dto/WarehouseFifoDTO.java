package com.promex.productionmanagement.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WarehouseFifoDTO {
    private String barcode;
    private String productName;
    private String categoryName;
    private Double quantity;
    private String measurementName;
    private Double cost;
    private Double amount;
    private String warehouseName;
}
