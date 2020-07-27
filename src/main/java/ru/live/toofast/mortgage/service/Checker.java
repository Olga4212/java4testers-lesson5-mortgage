package ru.live.toofast.mortgage.service;

import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

public interface Checker {
    public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest);
}
