package in.cg.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSalaryCalculatorTest {

    EmployeeSalaryCalculator calc =
            new EmployeeSalaryCalculator(20000, 5000, 10);

    @Test
    void testValidateSalary() {
        assertDoesNotThrow(() -> calc.validateSalary());
    }

    @Test
    void testHRA() {
        assertEquals(4000, calc.calculateHRA());
    }

    @Test
    void testDA() {
        assertEquals(2000, calc.calculateDA());
    }

    @Test
    void testGrossSalary() {
        assertEquals(31000, calc.calculateGrossSalary());
    }

    @Test
    void testNetSalary() {
        assertEquals(27900, calc.calculateNetSalary());
    }

    // Negative Tests

    @Test
    void testInvalidBasicSalary() {
        EmployeeSalaryCalculator c =
                new EmployeeSalaryCalculator(0, 2000, 10);

        assertThrows(IllegalArgumentException.class,
                () -> c.validateSalary());
    }

    @Test
    void testNegativeSalary() {
        EmployeeSalaryCalculator c =
                new EmployeeSalaryCalculator(-1000, 2000, 10);

        assertThrows(IllegalArgumentException.class,
                () -> c.validateSalary());
    }

    @Test
    void testInvalidTax() {
        EmployeeSalaryCalculator c =
                new EmployeeSalaryCalculator(20000, 2000, 120);

        assertThrows(IllegalArgumentException.class,
                () -> c.validateSalary());
    }
}
