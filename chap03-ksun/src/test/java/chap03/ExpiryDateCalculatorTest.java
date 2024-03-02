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

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 30))
                .billingDate(LocalDate.of(2024, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData2, LocalDate.of(2024, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 5, 31))
                .billingDate(LocalDate.of(2024, 6, 30))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData3, LocalDate.of(2024, 7, 31));
    }

    @Test
    void pay_over_10k_won() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(20_000)
                        .build(), 
                LocalDate.of(2024, 5, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(30_000)
                        .build(), 
                LocalDate.of(2024, 6, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 31))
                        .payAmount(60_000)
                        .build(), 
                LocalDate.of(2024, 9, 30));
    }

    @Test
    void firstBillingDate_not_same_with_expiryDate_over_10k_won() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024, 1, 31))
                        .billingDate(LocalDate.of(2024, 2, 28))
                        .payAmount(20_000)
                        .build(), 
                LocalDate.of(2024, 4, 30));
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024, 3, 31))
                        .billingDate(LocalDate.of(2024, 4, 30))
                        .payAmount(30_000)
                        .build(), 
                LocalDate.of(2024, 7, 31));
    }

    private void assertExpiryDate(
        PayData payData, LocalDate expectedExpiryDate
    )  {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
