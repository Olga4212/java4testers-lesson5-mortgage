package ru.live.toofast.mortgage.model;

import java.io.Serializable;

public class MortgageRequest implements Serializable {

    private String name;
    private Long period;
    private Long salary;
    private Long creditAmount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Long creditAmount) {
        this.creditAmount = creditAmount;
    }


    @Override
    public String toString() {
        return "MortgageRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
