package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.ProductWarehouse;
import com.promex.productionmanagement.entities.dto.ProductWarehouseDTO;
import com.promex.productionmanagement.entities.dto.WarehouseFifoDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductWarehouseService {

    Result addProductToWarehouse(ProductWarehouseDTO dto);

    Result removeAll(List<ProductWarehouse> productWarehouses);

    DataResult<List<ProductWarehouse>> getAllWarehouseData(Integer productId);

    DataResult<List<ProductWarehouse>> getAllWarehouseDataToGivenDateTime(LocalDateTime dateTime);

    DataResult<List<WarehouseFifoDTO>> getAllDataFifo(LocalDateTime dateTime);

    DataResult<List<ProductWarehouse>> getAllByDateTime(LocalDateTime dateTime);

    Result update(ProductWarehouseDTO dto);

}
