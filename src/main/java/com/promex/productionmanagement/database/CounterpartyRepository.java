package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {

    Counterparty getByCounterpartyIdAndState(Integer counterpartyId, int state);

    List<Counterparty> getAllByState(int state);

    List<Counterparty> getAllByCounterpartyNameStartsWithAndState(String counterpartyName, int state);

}
