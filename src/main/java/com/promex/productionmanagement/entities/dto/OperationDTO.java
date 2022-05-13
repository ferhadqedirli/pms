package com.promex.productionmanagement.entities.dto;

import com.promex.productionmanagement.entities.CurrencyType;
import com.promex.productionmanagement.entities.Operation;
import com.promex.productionmanagement.entities.OperationType;
import com.promex.productionmanagement.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OperationDTO {
    private Long operationId;
    private OperationType operationType;
    private LocalDateTime operationDate;
    private String operationCode;
    private double totalAmount;
    private CurrencyType currencyType;
    private WarehouseDTO warehouse;
    private CashDTO cash;
    private CounterpartyDTO counterparty;
    private List<ProductDTO> products = new ArrayList<>();
    private String description;
    private double payment;

    private int state = 1;

    public OperationDTO(Operation operation) {
        this.operationId = operation.getOperationId();
        this.operationType = operation.getOperationType();
        this.operationDate = operation.getOperationDate();
        this.operationCode = operation.getOperationCode();
        this.totalAmount = operation.getTotalAmount();
        this.currencyType = operation.getCurrencyType();
        this.warehouse = new WarehouseDTO(operation.getWarehouse());
        this.cash = new CashDTO(operation.getCash());
        this.counterparty = new CounterpartyDTO(operation.getCounterparty());
        this.products = getProducts(operation.getProducts());
        this.description = operation.getDescription();
        this.payment = operation.getPayment();
        this.state = operation.getState();
    }

    public OperationDTO() {
    }

    private List<ProductDTO> getProducts(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products) {
            productDTOS.add(new ProductDTO(product));
        }
        return productDTOS;
    }
}
