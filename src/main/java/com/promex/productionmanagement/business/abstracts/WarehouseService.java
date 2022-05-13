package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Warehouse;
import com.promex.productionmanagement.entities.dto.WarehouseDTO;

import java.util.List;

public interface WarehouseService {

    Result addWarehouse(WarehouseDTO dto);

    Result updateWarehouse(WarehouseDTO dto);

    Result removeWarehouse(Integer id);

    DataResult<Warehouse> getByWarehouseIdAndState(Integer warehouseId);

    DataResult<List<Warehouse>> getAllByWarehouseNameStartsWithAndStateOrderByWarehouseName(String warehouseName);

    DataResult<List<Warehouse>> getAllByStateOrderByWarehouseName();

}
