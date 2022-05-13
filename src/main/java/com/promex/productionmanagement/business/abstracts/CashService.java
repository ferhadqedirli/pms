package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Cash;
import com.promex.productionmanagement.entities.dto.CashDTO;
import com.promex.productionmanagement.entities.dto.CashTransferDTO;

import java.util.List;

public interface CashService {
    Result addCash(CashDTO dto);

    DataResult<Cash> addAssets(Integer cashId, Double assets);

    DataResult<Cash> cashOutflow(Integer cashId, Double assets);

    Result updateCash(CashDTO dto);

    Result removeCash(Integer cashId);

    Result transferCash(CashTransferDTO dto);

    DataResult<Cash> getByCashIdAndState(Integer cashId);

    DataResult<List<Cash>> getAllByCashNameStartsWithAndStateOrderByCashNameDesc(String prefix);

    DataResult<List<Cash>> getAllByStateOrderByCashNameDesc();
}
