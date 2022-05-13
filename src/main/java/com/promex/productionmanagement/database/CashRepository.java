package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Cash;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CashRepository extends JpaRepository<Cash, Integer> {

    Cash getByCashIdAndState(Integer cashId, int state);

    List<Cash> getAllByCashNameStartsWithAndStateOrderByCashNameDesc(String prefix, int state);

    List<Cash> getAllByStateOrderByCashNameDesc(int state);

}
