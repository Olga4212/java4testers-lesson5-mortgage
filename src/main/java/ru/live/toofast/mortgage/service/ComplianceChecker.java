package ru.live.toofast.mortgage.service;

import org.springframework.stereotype.Service;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ComplianceChecker implements Checker {
    private Set<String> terrorists = new HashSet<>(Arrays.asList("Ivan", "Semen"));

    @Override
    public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest) {
        if (terrorists.contains(mortgageRequest.getName())) {
            return MortgageApplication.DeclineReason.TERRORIST;
        }
        return MortgageApplication.DeclineReason.NONE;
    }
}
