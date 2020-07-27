package ru.live.toofast.mortgage.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.live.toofast.mortgage.entity.MortgageApplication;
import ru.live.toofast.mortgage.model.MortgageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

class CalculatorTest {

    @ParameterizedTest
    @MethodSource("checkTestData")
    void checkTest(MortgageApplication.DeclineReason expectedResult, List<Checker> checkers) {
        Calculator calculator = new Calculator(checkers);
        MortgageRequest mortgageRequest = new MortgageRequest();

        Assertions.assertThat(calculator.check(mortgageRequest))
                .isEqualTo(expectedResult);

    }

    public static Stream<Arguments> checkTestData() {
        return Stream.of(
                Arguments.of(
                        MortgageApplication.DeclineReason.LOW_SALARY,
                        new ArrayList<Checker>(
                                Arrays.asList(
                                    new CheckerMock(MortgageApplication.DeclineReason.NONE),
                                    new CheckerMock(MortgageApplication.DeclineReason.LOW_SALARY)
                                )
                        )
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.SCORING_FAILED,
                        new ArrayList<Checker>(
                                Arrays.asList(
                                        new CheckerMock(MortgageApplication.DeclineReason.SCORING_FAILED),
                                        new CheckerMock(MortgageApplication.DeclineReason.NONE)
                                )
                        )
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.SCORING_FAILED,
                        new ArrayList<Checker>(
                                Arrays.asList(
                                        new CheckerMock(MortgageApplication.DeclineReason.SCORING_FAILED),
                                        new CheckerMock(MortgageApplication.DeclineReason.LOW_SALARY)
                                )
                        )
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.NONE,
                        new ArrayList<Checker>(
                                Arrays.asList(
                                        new CheckerMock(MortgageApplication.DeclineReason.NONE),
                                        new CheckerMock(MortgageApplication.DeclineReason.NONE)
                                )
                        )
                ),
                Arguments.of(
                        MortgageApplication.DeclineReason.NONE,
                        new ArrayList<Checker>()
                )
        );
    }

    public static class CheckerMock implements Checker {
        private MortgageApplication.DeclineReason result;

        public CheckerMock(MortgageApplication.DeclineReason result) {
            this.result = result;
        }

        @Override
        public MortgageApplication.DeclineReason check(MortgageRequest mortgageRequest) {
            return result;
        }
    }
}