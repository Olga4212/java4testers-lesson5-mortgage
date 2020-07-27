package ru.live.toofast.mortgage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageList;
import ru.live.toofast.mortgage.model.MortgageRequest;
import ru.live.toofast.mortgage.model.MortgageResponse;
import ru.live.toofast.mortgage.repository.MortgageApplicationRepository;
import ru.live.toofast.mortgage.repository.MortgageApplicationSpecification;
import ru.live.toofast.mortgage.service.Calculator;

@RestController
public class MortgageController {

    private final MortgageApplicationRepository repository;
    private final Calculator calculator;

    public MortgageController(MortgageApplicationRepository repository, Calculator calculator) {
        this.repository = repository;
        this.calculator = calculator;
    }


    @GetMapping("/mortgages")
    public MortgageList getAll(){
        return new MortgageList(repository.findAll());
    }

    @GetMapping("/mortgages/successful")
    public MortgageList getSuccessful(){
        MortgageApplicationSpecification spec = new MortgageApplicationSpecification(MortgageApplication.Status.SUCCESS);
        return new MortgageList(repository.findAll(spec));
    }

    @GetMapping("/mortgages/declined")
    public MortgageList getDeclined(){
        MortgageApplicationSpecification spec = new MortgageApplicationSpecification(MortgageApplication.Status.DECLINE);
        return new MortgageList(repository.findAll(spec));
    }

    @PostMapping("/mortgage")
    public MortgageResponse register(@RequestBody MortgageRequest request){

        MortgageApplication mortgageApplication = new MortgageApplication();
        mortgageApplication.setName(request.getName());

        MortgageApplication.DeclineReason reason = calculator.check(request);
        mortgageApplication.setStatus(
                reason != MortgageApplication.DeclineReason.NONE
                ? MortgageApplication.Status.DECLINE
                : MortgageApplication.Status.SUCCESS
        );
        mortgageApplication.setDeclineReason(reason);
        mortgageApplication = repository.save(mortgageApplication);

        MortgageResponse response = new MortgageResponse();
        response.setRequest(request);
        response.setId(mortgageApplication.getId());
        response.setStatus(mortgageApplication.getStatus());

        return response;
    }


}
