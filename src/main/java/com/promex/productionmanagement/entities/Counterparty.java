package com.promex.productionmanagement.entities;

import com.promex.productionmanagement.entities.dto.CounterpartyDTO;
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
@Table(name = "counterparty")
public class Counterparty implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer counterpartyId;

    @Column(nullable = false)
    private String counterpartyName;

    @Column(precision = 6, scale = 2)
    @ColumnDefault("0.0")
    private Double counterpartyAccount;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Counterparty(CounterpartyDTO dto) {
        this.counterpartyName = dto.getCounterpartyName();
        this.state = dto.getState();
    }

}
