package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount){
        return billingDate.plusMonths(1);

    }
}
