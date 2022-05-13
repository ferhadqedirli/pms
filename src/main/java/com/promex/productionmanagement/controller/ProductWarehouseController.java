package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.ProductWarehouseService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.ProductWarehouse;
import com.promex.productionmanagement.entities.dto.ProductWarehouseDTO;
import com.promex.productionmanagement.entities.dto.WarehouseDTO;
import com.promex.productionmanagement.entities.dto.WarehouseFifoDTO;
import com.promex.productionmanagement.entities.dto.WarehouseTransferDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class ProductWarehouseController {

    private final ProductWarehouseService service;

    public ProductWarehouseController(ProductWarehouseService service) {
        this.service = service;
    }

    @GetMapping("/all-fifo")
    public DataResult<List<WarehouseFifoDTO>> getAllDataFifo(@RequestParam("dateTime")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return service.getAllDataFifo(dateTime);
    }

    @PostMapping("/add/product")
    public Result addProductToWarehouse(@RequestBody ProductWarehouseDTO dto) {
        return service.addProductToWarehouse(dto);
    }

    @GetMapping("/upto-given-datetime")
    public DataResult<List<ProductWarehouse>> getAllUpToGivenDateTime(@RequestParam("dateTime")
                                                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return service.getAllWarehouseDataToGivenDateTime(dateTime);
    }
}
