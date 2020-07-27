package ru.live.toofast.mortgage.service;

import org.springframework.stereotype.Service;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

@Service
public class PaymentAmountCalculator implements Checker {
    @Override
    public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest) {
        if (mortgageRequest.getCreditAmount() / mortgageRequest.getPeriod() > mortgageRequest.getSalary() / 2) {
            return MortgageApplication.DeclineReason.LOW_SALARY;
        }

        return MortgageApplication.DeclineReason.NONE;
    }
}
