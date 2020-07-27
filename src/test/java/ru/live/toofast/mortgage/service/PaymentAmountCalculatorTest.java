package ru.live.toofast.mortgage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.stream.Stream;

class PaymentAmountCalculatorTest {

    @ParameterizedTest
    @MethodSource("checkTestData")
    void checkTest(MortgageApplication.DeclineReason expectedResult, long creditAmount, long period, long salary) {
        MortgageRequest request = new MortgageRequest();
        request.setCreditAmount(creditAmount);
        request.setPeriod(period);
        request.setSalary(salary);

        PaymentAmountCalculator checker = new PaymentAmountCalculator();

        Assertions.assertThat(checker.check(request))
                .isEqualTo(expectedResult);

    }

    public static Stream<Arguments> checkTestData() {
        return Stream.of(
                Arguments.of(
                        MortgageApplication.DeclineReason.LOW_SALARY,
                        1002,
                        2,
                        1000
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.NONE,
                        1000,
                        2,
                        1000
                )
        );
    }
}