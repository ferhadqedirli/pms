package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.MeasurementService;
import com.promex.productionmanagement.core.*;
import com.promex.productionmanagement.database.MeasurementRepository;
import com.promex.productionmanagement.entities.Measurement;
import com.promex.productionmanagement.entities.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementManager implements MeasurementService {
    private final MeasurementRepository repository;

    @Autowired
    public MeasurementManager(MeasurementRepository measurementRepository) {
        this.repository = measurementRepository;
    }

    @Override
    public Result addMeasurement(MeasurementDTO dto) {
        try {
            Measurement measurement = new Measurement(dto);
            measurement.setState(1);
            repository.save(measurement);
            return new SuccessResult("Measurement added :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Measurement not added : Please try again");
        }
    }

    @Override
    public Result updateMeasurement(MeasurementDTO dto) {
        try {
            Measurement measurement1 = repository.getMeasurementByMeasurementIdAndState(dto.getMeasurementId(), 1);
            measurement1.setMeasurementName(dto.getMeasurementName());
            repository.save(measurement1);
            return new SuccessResult("Measurement update : ");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Measurement not update :Please try again");
        }
    }

    @Override
    public Result removeMeasurement(Integer id) {
        try {
            Measurement measurement = repository.getMeasurementByMeasurementIdAndState(id, 1);
            measurement.setState(0);
            repository.save(measurement);
            return new SuccessResult("Measurement successful delete :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public DataResult<Measurement> getMeasurementByMeasurementIdAndState(Integer measurementId) {
        try {
            return new SuccessDataResult<>(repository.getMeasurementByMeasurementIdAndState(measurementId, 1), "Data Listed");
        } catch (Exception ex) {
            return new ErrorDataResult<>("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public DataResult<List<Measurement>> getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(String prefix) {
        try {
            return new SuccessDataResult<>(repository.getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(prefix, 1), "Data Listed :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>("Nothing found in this name.Please try again");
        }
    }

    @Override
    public DataResult<List<Measurement>> getAllByStateOrderByMeasurementName() {
        return new SuccessDataResult<>(repository.getAllByStateOrderByMeasurementName(1), "Data Listed");
    }
}
