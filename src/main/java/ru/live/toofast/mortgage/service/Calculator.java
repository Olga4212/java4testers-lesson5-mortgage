package ru.live.toofast.mortgage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.List;

@Service
public class Calculator {

    private List<Checker> checkers;

    public Calculator(List<Checker> checkers) {
        this.checkers = checkers;
    }

    public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest) {
        for (Checker checker : this.checkers) {
            MortgageApplication.DeclineReason reason = checker.check(mortgageRequest);
            if (reason != MortgageApplication.DeclineReason.NONE) {
                return reason;
            }
        }
        return MortgageApplication.DeclineReason.NONE;
    }
}
