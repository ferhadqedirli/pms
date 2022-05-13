package com.promex.productionmanagement.business.concretes;

import com.promex.productionmanagement.business.abstracts.CashService;
import com.promex.productionmanagement.core.*;
import com.promex.productionmanagement.database.CashRepository;
import com.promex.productionmanagement.entities.Cash;
import com.promex.productionmanagement.entities.dto.CashDTO;
import com.promex.productionmanagement.entities.dto.CashTransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class CashManager implements CashService {

    private final CashRepository cashRepository;

    @Autowired
    public CashManager(CashRepository cashRepository) {
        this.cashRepository = cashRepository;
    }

    @Override

    public Result addCash(CashDTO dto) {
        try {
            Cash cash = new Cash(dto);
            cash.setState(1);
            cashRepository.save(cash);
            return new SuccessResult("Cash added :");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Cash not added : Please try again");
        }
    }

    @Override
    public DataResult<Cash> cashOutflow(Integer cashId, Double assets) {
        try {
            Cash cash = cashRepository.getByCashIdAndState(cashId, 1);
            cash.setAssets(cash.getAssets() - assets);
            cashRepository.save(cash);
            return new SuccessDataResult<>(cash, "Cash assets");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>(null, "Cash not assets : Please try again");
        }
    }

    @Override
    public DataResult<Cash> addAssets(Integer cashId, Double assets) {
        try {
            Cash cash = cashRepository.getByCashIdAndState(cashId, 1);
            cash.setAssets(assets + cash.getAssets());
            cashRepository.save(cash);
            return new SuccessDataResult<>(cash, "Cash assets");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>(null, "Cash not assets : Please try again");
        }
    }

    @Override
    public Result updateCash(CashDTO dto) {
        try {
            Cash cash = cashRepository.getByCashIdAndState(dto.getCashId(), 1);
            cash.setCashName(dto.getCashName());
            cashRepository.save(cash);
            return new SuccessResult("Cash update");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("Cash not update :Please try again");
        }
    }

    @Override
    public Result removeCash(Integer cashId) {
        try {
            Cash cash = cashRepository.getByCashIdAndState(cashId, 1);
            cash.setState(0);
            cashRepository.save(cash);
            return new SuccessResult("Cash delete ");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorResult("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public Result transferCash(CashTransferDTO dto) {
        try {
            Cash sender = cashRepository.getByCashIdAndState(dto.getDeliveringCashId(), 1);
            Cash receiver = cashRepository.getByCashIdAndState(dto.getReceivingCashId(), 1);
            if (sender.getAssets() < dto.getAssets()) {
                throw new IllegalArgumentException("There is not enough money at the checkout");
            }
            sender.setAssets(sender.getAssets() - dto.getAssets()); // 1500 -500=1000
            receiver.setAssets(receiver.getAssets() + dto.getAssets()); // 1000+500=1500
            List<Cash> cashLists = Arrays.asList(sender, receiver);
            cashRepository.saveAll(cashLists);
            return new SuccessResult("Cash success transfer");
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorResult("There is not enough money at the checkout");
        }

    }

    @Override
    public DataResult<Cash> getByCashIdAndState(Integer cashId) {
        try {
            return new SuccessDataResult<>(cashRepository.getByCashIdAndState(cashId, 1), "Data Listed");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>("The ID you entered is incorrect. Please try again");
        }
    }

    @Override
    public DataResult<List<Cash>> getAllByCashNameStartsWithAndStateOrderByCashNameDesc(String prefix) {
        try {
            return new SuccessDataResult<>(cashRepository.getAllByCashNameStartsWithAndStateOrderByCashNameDesc(prefix, 1), "Data Listed");
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ErrorDataResult<>("Nothing found in this name.Please try again");
        }
    }

    @Override
    public DataResult<List<Cash>> getAllByStateOrderByCashNameDesc() {
        return new SuccessDataResult<>(cashRepository.getAllByStateOrderByCashNameDesc(1), "Data Listed");
    }
}
