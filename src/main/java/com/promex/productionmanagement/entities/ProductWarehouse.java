package com.promex.productionmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_warehouse")
public class ProductWarehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double quantity;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double cost;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double amount;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    public ProductWarehouse(Warehouse warehouse, Product product, Double quantity, Double amount, LocalDateTime dateTime) {
        this.product = product;
        this.warehouse = warehouse;
        this.quantity = quantity;
        this.amount = amount;
        this.dateTime = dateTime;
    }

}
