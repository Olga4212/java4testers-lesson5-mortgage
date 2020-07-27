package ru.live.toofast.mortgage.model;

import ru.live.toofast.mortgage.entity.MortgageApplication;

import java.io.Serializable;

public class MortgageResponse implements Serializable {

    private MortgageRequest request;
    private Long id;
    private MortgageApplication.Status status;


    public MortgageRequest getRequest() {
        return request;
    }

    public void setRequest(MortgageRequest request) {
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MortgageApplication.Status getStatus() {
        return status;
    }

    public void setStatus(MortgageApplication.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MortgageApplication{" +
                "request=" + request +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }

}
