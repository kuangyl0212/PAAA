import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {
    @Test
    @DisplayName("1 + 1 = 2")
    void addTwoNumbers() {
        assertEquals(2, Calculator.add(1, 1));
    }
}
