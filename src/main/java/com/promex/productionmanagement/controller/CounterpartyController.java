package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.CounterpartyService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Counterparty;
import com.promex.productionmanagement.entities.dto.CounterpartyDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counterparty")
public class CounterpartyController {

    private final CounterpartyService service;

    public CounterpartyController(CounterpartyService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Result addCounterparty(@RequestBody CounterpartyDTO dto) {
        return service.addCounterparty(dto);
    }

    @PostMapping("/update")
    public Result updateCounterparty(@RequestBody CounterpartyDTO dto) {
        return service.updateCounterparty(dto);
    }

    @PostMapping("/remove")
    public Result removeCounterparty(@RequestParam Integer id) {
        return service.removeCounterparty(id);
    }

    @GetMapping("/all")
    public DataResult<List<Counterparty>> getAllCounterparty() {
        return service.getAllByState();
    }

    @GetMapping("/by-id")
    public DataResult<Counterparty> getCounterpartyById(@RequestParam Integer id) {
        return service.getByCounterpartyIdAndState(id);
    }

    @GetMapping("/by-name")
    public DataResult<List<Counterparty>> getAllCounterpartyByName(@RequestParam String name) {
        return service.getAllByCounterpartyNameStartsWithAndState(name);
    }

}
