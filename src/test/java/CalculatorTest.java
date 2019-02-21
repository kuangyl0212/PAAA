import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    @DisplayName("1 + 1 = 2")
    void addTwoNumbers() {
        assertEquals(2, Calculator.add(1, 1));
    }

    @Test
    @DisplayName("1 - 1 = 0")
    void subtractTwoNumber() {
        assertEquals(0, Calculator.subtract(1,1));
    }

    @Test
    @DisplayName("1 * 1 = 1")
    void multiplyTwoNumber() {
        assertEquals(1, Calculator.multiply(1,1));
    }

    @Test
    @DisplayName("1 / 1 = 1")
    void divideTwoNumber() {
        assertEquals(1, Calculator.divide(1,1));
    }
}
