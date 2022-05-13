package com.promex.productionmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promex.productionmanagement.entities.dto.MeasurementDTO;
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
@Table(name = "measurement")
public class Measurement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer measurementId;

    @Column(nullable = false)
    private String measurementName;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int state;

    public Measurement(MeasurementDTO dto) {
        this.measurementName = dto.getMeasurementName();
    }

}
