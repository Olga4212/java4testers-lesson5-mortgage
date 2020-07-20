package ru.live.toofast.mortgage.model;

import ru.live.toofast.mortgage.entity.MortgageApplication;

import java.io.Serializable;
import java.util.List;

public class MortgageList implements Serializable {

    List<MortgageApplication> values;

    public MortgageList(List<MortgageApplication> values) {
        this.values = values;
    }

    public MortgageList() {
    }

    public List<MortgageApplication> getValues() {
        return values;
    }

    public void setValues(List<MortgageApplication> values) {
        this.values = values;
    }
}
