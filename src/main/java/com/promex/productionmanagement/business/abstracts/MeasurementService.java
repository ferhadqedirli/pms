package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Measurement;
import com.promex.productionmanagement.entities.dto.MeasurementDTO;

import java.util.List;

public interface MeasurementService {
    Result addMeasurement(MeasurementDTO dto);

    Result updateMeasurement(MeasurementDTO dto);

    Result removeMeasurement(Integer id);

    DataResult<Measurement> getMeasurementByMeasurementIdAndState(Integer measurementId);

    DataResult<List<Measurement>> getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(String prefix);

    DataResult<List<Measurement>> getAllByStateOrderByMeasurementName();
}
