package org.firstPF.calculator;


import org.firstPF.calculator.Calculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.ByteArrayInputStream;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void setUp(){
        System.out.println("Seting up");
    }

    @AfterAll
    static void cleanUp(){
        System.out.println("Closing up");
    }

    @BeforeEach
    void beforeEach() {
        calculator = new Calculator();
    }

    @Test
    public void testIntegerDivision_WhenFourIsDividedByTwo_ShouldReturnTwo() {

        int dividend = 4;
        int divisor = 2;
        int expectedResult = 2;

        int result = calculator.integerDivision(dividend,divisor);

        Assertions.assertEquals(expectedResult,result);
    }

    @Test
    public void testIntegerDivision_WhenZeroIsDividedByTwo_ShouldThrowArithmeticException() {

        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";


        ArithmeticException actualException = Assertions.assertThrows(ArithmeticException.class, ()-> {
                    calculator.integerDivision(dividend, divisor);
                }, "Division by zero should have be thrown");

        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/integerSubtraction.csv")
    public void testIntegerSubtraction(int minuend, int subtrahend, int expectedResult) {

        System.out.println("Running Test " + minuend +" - " + subtrahend + " = "+ expectedResult);

        int actualResult = calculator.integerSubtraction(minuend,subtrahend);

        Assertions.assertEquals(expectedResult,actualResult);
    }

    @Test
    public void testEnterNumber() {

        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        Calculator calculator1 = new Calculator();


        int result = calculator1.enterNumber();


        Assertions.assertEquals(42, result);
    }


}
