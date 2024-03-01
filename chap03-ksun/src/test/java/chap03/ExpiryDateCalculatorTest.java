package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void pay_10K_won(){
        assertExpiryDate(
                LocalDate.of(2024, 3, 1), 10_000, 
                LocalDate.of(2024, 4, 1));
        assertExpiryDate(
                LocalDate.of(2024, 5, 5), 10_000, 
                LocalDate.of(2024, 6, 5));
    }

    private void assertExpiryDate(
        LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate
    )  {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
