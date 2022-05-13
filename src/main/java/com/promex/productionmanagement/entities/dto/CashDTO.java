package com.promex.productionmanagement.entities.dto;

import com.promex.productionmanagement.entities.Cash;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashDTO {
    private Integer cashId;
    private Double assets;
    private String cashName;

    public CashDTO(Cash cash) {
        this.cashId = cash.getCashId();
        this.assets = cash.getAssets();
        this.cashName = cash.getCashName();
    }

    public CashDTO() {
    }
}
