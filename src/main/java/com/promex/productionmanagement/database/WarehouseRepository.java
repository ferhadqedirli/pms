package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Warehouse getByWarehouseIdAndState(Integer warehouseId, int state);

    List<Warehouse> getAllByWarehouseNameStartsWithAndStateOrderByWarehouseName(String warehouseName, int state);

    List<Warehouse> getAllByStateOrderByWarehouseName(int state);

}
