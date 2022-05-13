package com.promex.productionmanagement.entities;

import com.promex.productionmanagement.entities.dto.CashDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cash")
public class Cash implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cashId;

    @Column(nullable = false, unique = true)
    private String cashName;

    @Column(nullable = false, precision = 6, scale = 2)
    @ColumnDefault("0")
    private Double assets;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Cash(CashDTO dto) {
        this.cashName = dto.getCashName();
        this.assets = dto.getAssets();
    }


}
