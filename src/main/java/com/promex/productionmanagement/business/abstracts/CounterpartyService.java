package com.promex.productionmanagement.business.abstracts;

import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.entities.Counterparty;
import com.promex.productionmanagement.entities.dto.CounterpartyDTO;

import java.util.List;

public interface CounterpartyService {

    Result addCounterparty(CounterpartyDTO dto);

    Result updateCounterparty(CounterpartyDTO dto);

    Result removeCounterparty(Integer id);

    DataResult<Counterparty> getByCounterpartyIdAndState(Integer counterpartyId);

    DataResult<List<Counterparty>> getAllByState();

    DataResult<List<Counterparty>> getAllByCounterpartyNameStartsWithAndState(String counterpartyName);

    DataResult<Counterparty> lendToCounterparty(CounterpartyDTO dto, Double totalAmount, Double payment);

    DataResult<Counterparty> borrowingCounterparty(CounterpartyDTO dto, Double totalAmount, Double payment);
}
