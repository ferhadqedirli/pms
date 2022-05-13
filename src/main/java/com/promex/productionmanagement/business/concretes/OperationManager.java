package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.*;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.core.SuccessDataResult;
import com.promex.productionmanagement.database.OperationRepository;
import com.promex.productionmanagement.entities.*;
import com.promex.productionmanagement.entities.dto.CounterpartyDTO;
import com.promex.productionmanagement.entities.dto.OperationDTO;
import com.promex.productionmanagement.entities.dto.ProductDTO;
import com.promex.productionmanagement.entities.dto.ProductWarehouseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationManager implements OperationService {

    private final ProductWarehouseService productWarehouseService;
    private final CashService cashService;
    private final CounterpartyService counterpartyService;
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final OperationRepository repository;

    public OperationManager(ProductWarehouseService productWarehouseService, CashService cashService, CounterpartyService counterpartyService, ProductService productService, WarehouseService warehouseService, OperationRepository repository) {
        this.productWarehouseService = productWarehouseService;
        this.cashService = cashService;
        this.counterpartyService = counterpartyService;
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.repository = repository;
    }

    @Override
    public Result purchase(OperationDTO dto) {
        Operation operation = null;
        if (dto.getOperationId() != null) {
            operation = repository.getByOperationIdAndState(dto.getOperationId(), 1);
            List<ProductWarehouse> productWarehouses = productWarehouseService.getAllByDateTime(operation.getOperationDate()).getData();
            operation.setState(0);
            productWarehouseService.removeAll(productWarehouses);
            repository.save(fillOperation(operation, dto));
        } else {
            repository.save(fillOperation(new Operation(), dto));
        }
//        operation.setOperationCode(UUID.randomUUID().toString());
//        operation.setOperationDate(dto.getOperationDate());
//        operation.setOperationType(OperationType.PURCHASE);
//        operation.setCash(cashService.getByCashIdAndState(dto.getCash().getCashId()).getData());
//        operation.setCounterparty(counterpartyService.getByCounterpartyIdAndState(dto.getCounterparty().getCounterpartyId()).getData());
//        operation.setCurrencyType(dto.getCurrencyType());
//        operation.setDescription(dto.getDescription());
//        operation.setPayment(dto.getPayment());
//        operation.setProducts(getProducts(dto));
//        operation.setTotalAmount(getPurchaseTotalAmount(dto));
//        operation.setState(dto.getState());
//
//        operation.setWarehouse(warehouseService.getByWarehouseIdAndState(dto.getWarehouse().getWarehouseId()).getData());
//        productWarehouseService.addProductToWarehouse(new ProductWarehouseDTO(dto.getWarehouse(), dto.getProducts(), operation.getOperationDate()));
//        repository.save(operation);
//        debtToCounterparty(dto.getCounterparty().getCounterpartyId(), operation.getTotalAmount(), dto.getPayment());
//        cashOutflow(dto);
        return new SuccessDataResult<>(operation, "New purchase operation done");
    }

    @Override
    public Result updatePurchase(OperationDTO dto) {
        Operation operation = repository.getByOperationIdAndState(dto.getOperationId(), 1);
        List<ProductWarehouse> productWarehouses = productWarehouseService.getAllByDateTime(operation.getOperationDate()).getData();
        operation.setState(0);
        productWarehouseService.removeAll(productWarehouses);
//        Operation operation = repository.getByOperationIdAndState((long) dto.getOperationId(), 1);
//        operation.setOperationDate(dto.getOperationDate());
//        operation.setOperationType(OperationType.PURCHASE);
//        operation.setCash(cashService.getByCashIdAndState(dto.getCash().getCashId()).getData());
//        operation.setCounterparty(counterpartyService.getByCounterpartyIdAndState(dto.getCounterparty().getCounterpartyId()).getData());
//        operation.setCurrencyType(dto.getCurrencyType());
//        operation.setDescription(dto.getDescription());
//        operation.setProducts(getProducts(dto));
//        operation.setTotalAmount(getPurchaseTotalAmount(dto));
//        operation.setState(dto.getState());
//        operation.setWarehouse(warehouseService.getByWarehouseIdAndState(dto.getWarehouse().getWarehouseId()).getData());
//        productWarehouseService.update(new ProductWarehouseDTO(dto.getWarehouse(), dto.getProducts(), operation.getOperationDate()));
//        repository.save(operation);
//        debtToCounterparty(dto.getCounterparty().getCounterpartyId(), operation.getTotalAmount(), dto.getPayment());
//        cashOutflow(dto);
//        return new SuccessDataResult<>(operation, "New purchase operation done");
        return new SuccessDataResult<>(purchase(new OperationDTO(operation)), "Operation updated : ");
    }

    @Override
    public Result removePurchase(Long id) {
        Operation operation = repository.getByOperationIdAndState(id, 1);
        operation.setState(0);
        List<ProductWarehouse> productWarehouses = productWarehouseService.getAllByDateTime(operation.getOperationDate()).getData();
        repository.save(operation);
        return new SuccessDataResult<>(productWarehouseService.removeAll(productWarehouses), "Data deleted from db : ");
    }

    @Override
    public Result deletePurchase(Long id) {
        return null;
    }

    private List<Product> getProducts(OperationDTO dto) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : dto.getProducts()) {
            products.add(productService.getProductByProductIdAndState(productDTO.getProductId()).getData());
        }
        return products;
    }

    private double getPurchaseTotalAmount(OperationDTO dto) {
        double totalAmount = 0;
        for (ProductDTO productDTO : dto.getProducts()) {
            totalAmount += (productDTO.getQuantity() * productDTO.getCost());
        }
        return totalAmount;
    }

    private void debtToCounterparty(Integer counterpartyId, Double amount, Double payment) {
        Counterparty counterparty = counterpartyService.getByCounterpartyIdAndState(counterpartyId).getData();
        counterparty.setCounterpartyAccount(counterparty.getCounterpartyAccount() + amount - payment);
        counterpartyService.updateCounterparty(new CounterpartyDTO(counterparty));
    }

    private void cashOutflow(OperationDTO dto) {
        cashService.cashOutflow(dto.getCash().getCashId(), dto.getPayment());
    }

    private Operation fillOperation(Operation operation, OperationDTO dto) {
        operation.setOperationCode(UUID.randomUUID().toString());
        operation.setOperationDate(dto.getOperationDate());
        operation.setOperationType(OperationType.PURCHASE);
        operation.setCash(cashService.getByCashIdAndState(dto.getCash().getCashId()).getData());
        operation.setCounterparty(counterpartyService.getByCounterpartyIdAndState(dto.getCounterparty().getCounterpartyId()).getData());
        operation.setCurrencyType(dto.getCurrencyType());
        operation.setDescription(dto.getDescription());
        operation.setPayment(dto.getPayment());
        operation.setProducts(getProducts(dto));
        operation.setTotalAmount(getPurchaseTotalAmount(dto));
        operation.setState(dto.getState());

        operation.setWarehouse(warehouseService.getByWarehouseIdAndState(dto.getWarehouse().getWarehouseId()).getData());
        productWarehouseService.addProductToWarehouse(new ProductWarehouseDTO(dto.getWarehouse(), dto.getProducts(), operation.getOperationDate()));
        debtToCounterparty(dto.getCounterparty().getCounterpartyId(), operation.getTotalAmount(), dto.getPayment());
        cashOutflow(dto);
        return operation;
    }
}
