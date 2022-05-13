package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.WarehouseService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Warehouse;
import com.promex.productionmanagement.entities.dto.WarehouseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService service;

    @Autowired
    public WarehouseController(WarehouseService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result addWarehouse(@RequestBody WarehouseDTO dto) {
        return service.addWarehouse(dto);
    }

    @PostMapping("/update")
    public Result updateWarehouse(@RequestBody WarehouseDTO dto) {
        return service.updateWarehouse(dto);
    }

    @PostMapping("/remove")
    public Result removeWarehouse(@RequestParam Integer id) {
        return service.removeWarehouse(id);
    }

    @GetMapping("/all")
    public DataResult<List<Warehouse>> getAllWarehouse() {
        return service.getAllByStateOrderByWarehouseName();
    }

    @GetMapping("/by-name")
    public DataResult<List<Warehouse>> getAllWarehouseByName(@RequestParam String name) {
        return service.getAllByWarehouseNameStartsWithAndStateOrderByWarehouseName(name);
    }

    @GetMapping("/by-id")
    public DataResult<Warehouse> getWarehouseById(@RequestParam Integer id) {
        return service.getByWarehouseIdAndState(id);
    }
}
