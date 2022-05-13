package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.dto.OperationDTO;

public interface OperationService {

    Result purchase(OperationDTO dto);

    Result updatePurchase(OperationDTO dto);

    Result removePurchase(Long id);

    Result deletePurchase(Long id);

}
