package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.WarehouseService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.ErrorDataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.core.SuccessDataResult;
import com.promex.productionmanagement.database.WarehouseRepository;
import com.promex.productionmanagement.entities.Warehouse;
import com.promex.productionmanagement.entities.dto.WarehouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseManager implements WarehouseService {

    private final WarehouseRepository repository;

    @Autowired
    public WarehouseManager(WarehouseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result addWarehouse(WarehouseDTO dto) {
        Warehouse warehouse = new Warehouse(dto);
        repository.save(warehouse);
        return new SuccessDataResult<>("Warehouse added : ");
    }

    @Override
    public Result updateWarehouse(WarehouseDTO dto) {
        try {
            Warehouse warehouse = repository.getByWarehouseIdAndState(dto.getWarehouseId(), 1);
            warehouse.setWarehouseName(dto.getWarehouseName());
            repository.save(warehouse);
            return new SuccessDataResult<>("Warehouse updated : ");
        }catch (Exception e) {
            return new ErrorDataResult<>("Not found");
        }
    }

    @Override
    public Result removeWarehouse(Integer id) {
        try {
            Warehouse warehouse = repository.getByWarehouseIdAndState(id, 1);
            warehouse.setState(0);
            repository.save(warehouse);
            return new SuccessDataResult<>("Warehouse deleted : ");
        } catch (Exception e) {
            return new ErrorDataResult<>("Not found");
        }

    }

    @Override
    public DataResult<Warehouse> getByWarehouseIdAndState(Integer warehouseId) {
        Warehouse warehouse = repository.getByWarehouseIdAndState(warehouseId, 1);
        return new SuccessDataResult<>(warehouse, "Data listed : ");
    }

    @Override
    public DataResult<List<Warehouse>> getAllByWarehouseNameStartsWithAndStateOrderByWarehouseName(String warehouseName) {
        List<Warehouse> warehouses = repository.getAllByWarehouseNameStartsWithAndStateOrderByWarehouseName(warehouseName, 1);
        return new SuccessDataResult<>(warehouses, "Data listed : ");
    }

    @Override
    public DataResult<List<Warehouse>> getAllByStateOrderByWarehouseName() {
        List<Warehouse> warehouses = repository.getAllByStateOrderByWarehouseName(1);
        return new SuccessDataResult<>(warehouses, "Data listed : ");
    }
}
