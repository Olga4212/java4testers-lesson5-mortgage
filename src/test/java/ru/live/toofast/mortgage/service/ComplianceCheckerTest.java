package ru.live.toofast.mortgage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.stream.Stream;

class ComplianceCheckerTest {

    @ParameterizedTest
    @MethodSource("checkTestData")
    void checkTest(MortgageApplication.DeclineReason expectedResult, String name) {
        MortgageRequest request = new MortgageRequest();
        request.setName(name);

        ComplianceChecker checker = new ComplianceChecker();

        Assertions.assertThat(checker.check(request))
                .isEqualTo(expectedResult);

    }

    public static Stream<Arguments> checkTestData() {
        return Stream.of(
                Arguments.of(
                        MortgageApplication.DeclineReason.TERRORIST,
                        "Ivan"
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.NONE,
                        "Petr"
                )
        );
    }
}