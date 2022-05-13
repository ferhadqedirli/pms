package com.promex.productionmanagement.database;


import com.promex.productionmanagement.entities.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Measurement getMeasurementByMeasurementIdAndState(Integer measurementId, int state);

    List<Measurement> getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(String prefix, int state);

    List<Measurement> getAllByStateOrderByMeasurementName(int state);
}
