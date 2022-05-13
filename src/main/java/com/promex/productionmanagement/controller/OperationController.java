package com.promex.productionmanagement.controller;

import com.promex.productionmanagement.business.abstracts.OperationService;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.dto.OperationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation")
public class OperationController {

    private final OperationService service;

    @Autowired
    public OperationController(OperationService service) {
        this.service = service;
    }

    @PostMapping("/purchase")
    public Result purchase(@RequestBody OperationDTO dto) {
        return service.purchase(dto);
    }

    @PostMapping("/update-purchase")
    public Result updatePurchase(@RequestBody OperationDTO dto) {
        return service.purchase(dto);
    }

    @PostMapping("/delete-purchase")
    public Result deletePurchase(@RequestParam Long id) {
        return service.removePurchase(id);
    }

}
