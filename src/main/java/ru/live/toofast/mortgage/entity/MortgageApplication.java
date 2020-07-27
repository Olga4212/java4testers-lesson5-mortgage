package ru.live.toofast.mortgage.entity;

import javax.persistence.*;

@Entity
public class MortgageApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    private DeclineReason declineReason;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public DeclineReason getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(DeclineReason declineReason) {
        this.declineReason = declineReason;
    }

    public enum Status {
        SUCCESS,
        DECLINE
    }

    public enum DeclineReason {
        NONE,
        TERRORIST,
        SCORING_FAILED,
        LOW_SALARY
    }
}
