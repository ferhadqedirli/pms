package com.promex.productionmanagement.database;

import com.promex.productionmanagement.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    Operation getByOperationIdAndState(Long operationId, int state);

    void deleteByOperationId(Long id);

}
