package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.ProductService;
import com.promex.productionmanagement.business.abstracts.ProductWarehouseService;
import com.promex.productionmanagement.business.abstracts.WarehouseService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.core.SuccessDataResult;
import com.promex.productionmanagement.database.ProductWarehouseRepository;
import com.promex.productionmanagement.entities.ProductWarehouse;
import com.promex.productionmanagement.entities.dto.ProductDTO;
import com.promex.productionmanagement.entities.dto.ProductWarehouseDTO;
import com.promex.productionmanagement.entities.dto.WarehouseFifoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProductWarehouseManager implements ProductWarehouseService {

    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final ProductWarehouseRepository repository;

    @Autowired
    public ProductWarehouseManager(ProductService productService, WarehouseService warehouseService, ProductWarehouseRepository repository) {
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.repository = repository;
    }

    @Override
    public Result addProductToWarehouse(ProductWarehouseDTO dto) {
        List<ProductWarehouse> productWarehouseList = new ArrayList<>();
        for (ProductDTO productDTO : dto.getProducts()) {
            ProductWarehouse productWarehouse = new ProductWarehouse();
            productWarehouse.setProduct(productService.getProductByProductIdAndState(productDTO.getProductId()).getData());
            productWarehouse.setWarehouse(warehouseService.getByWarehouseIdAndState(dto.getWarehouse().getWarehouseId()).getData());
            productWarehouse.setAmount(productDTO.getQuantity() * productDTO.getCost());
            productWarehouse.setCost(productDTO.getCost());
            productWarehouse.setQuantity(productDTO.getQuantity());
            productWarehouse.setDateTime(dto.getDateTime());
            productWarehouseList.add(productWarehouse);
        }
        repository.saveAll(productWarehouseList);
        return new SuccessDataResult<>(productWarehouseList, "Products added to warehouse successfully");
    }

    @Override
    public Result removeAll(List<ProductWarehouse> productWarehouses) {
        repository.deleteAll(productWarehouses);
        return new SuccessDataResult<>("Products are deleted from db : ");
    }

    @Override
    public DataResult<List<ProductWarehouse>> getAllWarehouseData(Integer productId) {
        return new SuccessDataResult<>(repository.getByProduct_ProductIdOrderByDateTime(productId));
    }

    @Override
    public DataResult<List<ProductWarehouse>> getAllWarehouseDataToGivenDateTime(LocalDateTime dateTime) {
        return new SuccessDataResult<>(repository.getAllByGivenDateTime(dateTime));
    }

    @Override
    public DataResult<List<WarehouseFifoDTO>> getAllDataFifo(LocalDateTime dateTime) {
        return new SuccessDataResult<>(repository.getAllWarehouseDataFifo(dateTime), "Data listed : ");
    }

    @Override
    public DataResult<List<ProductWarehouse>> getAllByDateTime(LocalDateTime dateTime) {
        return new SuccessDataResult<>(repository.getAllByDateTime(dateTime), "Data listed : ");
    }

    @Override
    public Result update(ProductWarehouseDTO dto) {
        List<ProductWarehouse> productWarehouses = repository.getAllByDateTime(dto.getDateTime());
        repository.deleteAll(productWarehouses);
        return new SuccessDataResult<>(addProductToWarehouse(dto), "Warehouse updated : ");
    }
}
