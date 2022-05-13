package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.CashService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Cash;
import com.promex.productionmanagement.entities.dto.CashDTO;
import com.promex.productionmanagement.entities.dto.CashTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cash")
public class CashController {
    private final CashService service;

    @Autowired
    public CashController(CashService service) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    public Result addCash(@RequestBody CashDTO dto) {
        return service.addCash(dto);
    }

    @PostMapping(value = "/addAssets")
    public DataResult<Cash> addAssets(@RequestParam Integer cashId, @RequestParam Double assets) {
        return service.addAssets(cashId, assets);
    }

    @PostMapping(value = "/transferAssets")
    public Result transferCash( @RequestBody CashTransferDTO dto) {
        return service.transferCash(dto);
    }

    @PostMapping(value = "/update")
    public Result updateCash(@RequestBody CashDTO dto) {
        return service.updateCash(dto);
    }

    @PostMapping(value = "/delete")
    public Result removeCategory(@RequestParam Integer id) {
        return service.removeCash(id);
    }

    @GetMapping(value = "/getAll")
    public DataResult<List<Cash>> getAllByStateOrderByCashNameDesc() {
        return service.getAllByStateOrderByCashNameDesc();
    }

    @GetMapping(value = "/getByCashIdAndState")
    public DataResult<Cash> getByCashIdAndState(@RequestParam Integer cashId) {
        return service.getByCashIdAndState(cashId);
    }

    @GetMapping(value = "/getAllByCashNameStartsWithAndStateOrderByCashNameDesc")
    public DataResult<List<Cash>> getAllByCashNameStartsWithAndStateOrderByCashNameDesc(@RequestParam String prefix) {
        return service.getAllByCashNameStartsWithAndStateOrderByCashNameDesc(prefix);
    }
}
