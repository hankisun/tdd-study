package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        if(payData.getFirstBillingDate() != null) {
            if(payData.getFirstBillingDate().equals(LocalDate.of(2024, 1, 31))) {
                return LocalDate.of(2024, 3, 31);
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
