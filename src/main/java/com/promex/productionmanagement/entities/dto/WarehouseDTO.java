package com.promex.productionmanagement.entities.dto;

import com.promex.productionmanagement.entities.Warehouse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseDTO {
    private Integer warehouseId;
    private String WarehouseName;

    private int state = 1;

    public WarehouseDTO(Warehouse warehouse) {
        this.warehouseId = warehouse.getWarehouseId();
        this.WarehouseName = warehouse.getWarehouseName();
        this.state = warehouse.getState();
    }

    public WarehouseDTO() {
    }
}
