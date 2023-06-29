import org.firstPF.Calculator;
import org.junit.jupiter.api.*;

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
    void beforeEach(){
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

    @Test
    public void testIntegerSubtraction() {
        Calculator calculator = new Calculator();

        int result = calculator.integerSubtraction(10,5);

        Assertions.assertEquals(5,result);
    }

    @Test
    public void testEnterNumber() {
        // Testovací vstup
        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Vytvoření instance třídy YourClass
        Calculator calculator1 = new Calculator();

        // Volání metody enterNumber()
        int result = calculator1.enterNumber();

        // Otestování výsledku
        Assertions.assertEquals(42, result);
    }


}
