package com.promex.productionmanagement.entities.dto;

import lombok.Data;

@Data
public class CashTransferDTO {
    private Integer receivingCashId;
    private Integer deliveringCashId;
    private double assets;
}
