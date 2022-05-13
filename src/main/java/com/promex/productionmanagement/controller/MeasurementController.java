package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.MeasurementService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Measurement;
import com.promex.productionmanagement.entities.dto.MeasurementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService service;

    @Autowired
    public MeasurementController(MeasurementService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result addMeasurement(@RequestBody MeasurementDTO dto) {
        return service.addMeasurement(dto);
    }

    @PostMapping("/update")
    public Result updateMeasurement(@RequestBody MeasurementDTO dto) {
        return service.updateMeasurement(dto);
    }

    @PostMapping("/remove")
    public Result removeMeasurement(@RequestParam Integer id) {
        return service.removeMeasurement(id);
    }

    @GetMapping("getMeasurementByMeasurementIdAndState")
    public DataResult<Measurement> getMeasurementByMeasurementIdAndState(@RequestParam Integer measurementId) {
        return service.getMeasurementByMeasurementIdAndState(measurementId);
    }

    @GetMapping("/getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName")
    public DataResult<List<Measurement>> getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(@RequestParam String prefix) {
        return service.getAllByMeasurementNameStartsWithAndStateOrderByMeasurementName(prefix);
    }

    @GetMapping("/getAll")
    public DataResult<List<Measurement>> getAllByStateOrderByMeasurementName() {
        return service.getAllByStateOrderByMeasurementName();
    }

}
