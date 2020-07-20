package ru.live.toofast.mortgage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.live.toofast.mortgage.entity.MortgageApplication;

public interface MortgageApplicationRepository extends JpaRepository<MortgageApplication, Long> {
}
