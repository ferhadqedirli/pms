package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.CounterpartyService;
import com.promex.productionmanagement.core.DataResult;
import com.promex.productionmanagement.core.ErrorDataResult;
import com.promex.productionmanagement.core.Result;
import com.promex.productionmanagement.core.SuccessDataResult;
import com.promex.productionmanagement.database.CounterpartyRepository;
import com.promex.productionmanagement.entities.Counterparty;
import com.promex.productionmanagement.entities.dto.CounterpartyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterpartyManager implements CounterpartyService {

    private final CounterpartyRepository repository;

    @Autowired
    public CounterpartyManager(CounterpartyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Result addCounterparty(CounterpartyDTO dto) {
        try {
            Counterparty counterparty = new Counterparty(dto);
            repository.save(counterparty);
            return new SuccessDataResult<>("Counterparty added : ");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public Result updateCounterparty(CounterpartyDTO dto) {
        try {
            Counterparty counterparty = repository.getByCounterpartyIdAndState(dto.getCounterpartyId(), 1);
            counterparty.setCounterpartyName(dto.getCounterpartyName());
            if (dto.getCounterpartyAccount() != null) {
                counterparty.setCounterpartyAccount(dto.getCounterpartyAccount());
            }
            repository.save(counterparty);
            return new SuccessDataResult<>("Counterparty updated :");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public Result removeCounterparty(Integer id) {
        try {
            Counterparty counterparty = repository.getByCounterpartyIdAndState(id, 1);
            counterparty.setState(0);
            repository.save(counterparty);
            return new SuccessDataResult<>("Counterparty deleted : ");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public DataResult<Counterparty> getByCounterpartyIdAndState(Integer counterpartyId) {
        try {
            Counterparty counterparty = repository.getByCounterpartyIdAndState(counterpartyId, 1);
            return new SuccessDataResult<>(counterparty, "Data listed : ");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public DataResult<List<Counterparty>> getAllByState() {
        try {
            List<Counterparty> counterparties = repository.getAllByState(1);
            return new SuccessDataResult<>(counterparties, "Data listed : ");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public DataResult<List<Counterparty>> getAllByCounterpartyNameStartsWithAndState(String counterpartyName) {
        try {
            List<Counterparty> counterparties = repository.getAllByCounterpartyNameStartsWithAndState(counterpartyName, 1);
            return new SuccessDataResult<>(counterparties, "Data listed : ");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorDataResult<>(e.getMessage());
        }
    }

    @Override
    public DataResult<Counterparty> lendToCounterparty(CounterpartyDTO dto, Double totalAmount, Double payment) {
        Counterparty counterparty = repository.getByCounterpartyIdAndState(dto.getCounterpartyId(), 1);
        counterparty.setCounterpartyAccount(counterparty.getCounterpartyAccount() - (totalAmount - payment));
        repository.save(counterparty);
        return new SuccessDataResult<>(counterparty, "Counterparty account updated : ");
    }

    public DataResult<Counterparty> borrowingCounterparty(CounterpartyDTO dto, Double totalAmount, Double payment) {
        Counterparty counterparty = repository.getByCounterpartyIdAndState(dto.getCounterpartyId(), 1);
        counterparty.setCounterpartyAccount(counterparty.getCounterpartyAccount() + (totalAmount - payment));
        repository.save(counterparty);
        return new SuccessDataResult<>(counterparty, "Counterparty account updated : ");
    }

}
