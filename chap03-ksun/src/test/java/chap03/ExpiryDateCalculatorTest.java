package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void pay_10K_won(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(10_000)
                        .build(), 
                LocalDate.of(2024, 4, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 5, 5))
                        .payAmount(10_000)
                        .build(), 
                LocalDate.of(2024, 6, 5));
    }

    @Test
    void next_month_expiryDate_is_less_than_this_month(){
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 1, 31))
                        .payAmount(10_000)
                        .build(), 
                LocalDate.of(2024, 2, 29));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 5, 31))
                        .payAmount(10_000)
                        .build(), 
                LocalDate.of(2024, 6, 30));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2025, 1, 31))
                        .payAmount(10_000)
                        .build(), 
                LocalDate.of(2025, 2, 28));
    }

    @Test
    void firstBillingDate_not_same_with_expiryDate() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 31))
                .billingDate(LocalDate.of(2024, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2024, 3, 31));
    }

    private void assertExpiryDate(
        PayData payData, LocalDate expectedExpiryDate
    )  {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
