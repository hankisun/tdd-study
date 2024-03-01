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

    @Test
    void next_month_expiryDate_is_less_than_this_month(){
        assertExpiryDate(
                LocalDate.of(2024, 1, 31), 10_000, 
                LocalDate.of(2024, 2, 29));
        assertExpiryDate(
                LocalDate.of(2024, 5, 31), 10_000, 
                LocalDate.of(2024, 6, 30));
        assertExpiryDate(
                LocalDate.of(2025, 1, 31), 10_000, 
                LocalDate.of(2025, 2, 28));
    }

    private void assertExpiryDate(
        LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate
    )  {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
