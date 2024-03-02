package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }
}
