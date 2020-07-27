package ru.live.toofast.mortgage.repository;

import org.springframework.data.jpa.domain.Specification;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.entity.MortgageApplication.Status;

import javax.persistence.criteria.*;

public class MortgageApplicationSpecification implements Specification<MortgageApplication> {

    private Status status;

    public MortgageApplicationSpecification(Status status) {
        this.status = status;
    }

    @Override
    public Predicate toPredicate(Root<MortgageApplication> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("status"), this.status);
    }
}
