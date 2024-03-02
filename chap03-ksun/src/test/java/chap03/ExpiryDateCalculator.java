package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    LocalDate calculateExpiryDate(PayData payData) {
        int addMonths = payData.getPayAmount() / 10_000;
        if(payData.getFirstBillingDate() != null) {
            LocalDate candidateExp = payData.getBillingDate().plusMonths(addMonths);
            if(payData.getFirstBillingDate().getDayOfMonth() != candidateExp.getDayOfMonth()) {
                if(YearMonth.from(candidateExp).lengthOfMonth() < 
                        payData.getFirstBillingDate().getDayOfMonth()) {
                    return candidateExp.withDayOfMonth(
                        YearMonth.from(candidateExp).lengthOfMonth());
                }
                return candidateExp.withDayOfMonth(
                        payData.getFirstBillingDate().getDayOfMonth());
            }
        }
        return payData.getBillingDate().plusMonths(addMonths);
    }
}
