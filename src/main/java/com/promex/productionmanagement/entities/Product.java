package com.promex.productionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promex.productionmanagement.entities.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "operations"})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = false)
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "measurement_id")
    private Measurement measurement;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double price;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ProductWarehouse> productWarehouses = new ArrayList<>();

    @ManyToMany(mappedBy = "products", cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private List<Operation> operations = new ArrayList<>();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Product(ProductDTO dto) {
        this.barcode = dto.getBarcode();
        this.category = dto.getCategory();
        this.measurement = dto.getMeasurement();
        this.productName = dto.getProductName();
        this.state = dto.getState();
        this.price = dto.getPrice();
    }
}


