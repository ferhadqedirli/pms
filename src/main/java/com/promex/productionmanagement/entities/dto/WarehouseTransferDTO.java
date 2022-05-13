package com.promex.productionmanagement.entities.dto;

import lombok.Data;

import java.util.List;

@Data
public class WarehouseTransferDTO {
    private Integer senderId;
    private Integer receiverId;
    private List<ProductDTO> products;
}
