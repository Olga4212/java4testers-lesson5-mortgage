package ru.live.toofast.mortgage.service;

import org.springframework.stereotype.Service;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.*;

@Service
public class CreditScoreChecker implements Checker {

    private Map<String, Double> scores;

    protected final double THRESHOLD = 75.;

    public CreditScoreChecker() {
        scores = new HashMap<>();
        scores.put("Ivan", 70.);
        scores.put("Semen", 80.);
        scores.put("Petr", 75.);
    }

    @Override
    public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest) {
        if (scores.getOrDefault(mortgageRequest.getName(), 100.) < THRESHOLD) {
            return MortgageApplication.DeclineReason.SCORING_FAILED;
        }
        return MortgageApplication.DeclineReason.NONE;
    }
}
