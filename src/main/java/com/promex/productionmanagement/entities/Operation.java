package com.promex.productionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promex.productionmanagement.entities.dto.OperationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "operation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "products"})
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operationId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OperationType operationType;

    @Column(nullable = false, name = "date_time")
    private LocalDateTime operationDate;

    @Column(nullable = false)
    private String operationCode;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currencyType;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "cash_id")
    private Cash cash;

    @ManyToOne
    @JoinColumn(name = "counterparty_id")
    private Counterparty counterparty;

    private double payment;

    //    @ManyToMany(mappedBy = "operations", cascade = CascadeType.MERGE)
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "operation_product",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    private String description;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Operation(OperationDTO dto) {
        this.operationType = dto.getOperationType();
        this.currencyType = dto.getCurrencyType();
        this.operationCode = dto.getOperationCode();
        this.description = dto.getDescription();
        this.totalAmount = dto.getTotalAmount();
        this.state = dto.getState();
    }

}
