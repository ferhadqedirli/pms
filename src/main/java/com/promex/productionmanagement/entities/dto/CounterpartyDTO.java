package com.promex.productionmanagement.entities.dto;

import com.promex.productionmanagement.entities.Counterparty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CounterpartyDTO {
    private Integer counterpartyId;
    private Double counterpartyAccount;
    private String counterpartyName;

    private int state = 1;

    public CounterpartyDTO(Counterparty counterparty) {
        this.counterpartyId = counterparty.getCounterpartyId();
        this.counterpartyAccount = counterparty.getCounterpartyAccount();
        this.counterpartyName = counterparty.getCounterpartyName();
        this.state = counterparty.getState();
    }

    public CounterpartyDTO() {
    }
}
