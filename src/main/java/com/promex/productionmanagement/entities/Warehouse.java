package com.promex.productionmanagement.entities;

import com.promex.productionmanagement.entities.dto.WarehouseDTO;
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
@Table(name = "warehouse")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer warehouseId;

    @Column(nullable = false)
    private String warehouseName;

//    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<ProductWarehouse> productWarehouses = new ArrayList<>();

//    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Operation> operations = new ArrayList<>();

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Warehouse(WarehouseDTO dto) {
        this.warehouseName = dto.getWarehouseName();
        this.state = dto.getState();
    }
}
