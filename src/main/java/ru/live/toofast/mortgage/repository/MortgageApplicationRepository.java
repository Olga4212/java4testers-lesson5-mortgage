package ru.live.toofast.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.live.toofast.mortgage.entity.MortgageApplication;

public interface MortgageApplicationRepository extends JpaRepository<MortgageApplication, Long>, JpaSpecificationExecutor<MortgageApplication> {

}
