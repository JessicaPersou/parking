package br.com.postech.parking.ticket.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

public class TicketPriceCalculator {
    private static final BigDecimal HOURLY_RATE = new BigDecimal("10.00");
    private static final BigDecimal MINIMUM_FEE = new BigDecimal("5.00");
    private static final int MINIMUM_MINUTES = 30;


    public BigDecimal calculateBasicPrice(LocalDateTime entryTime, LocalDateTime exitTime) {
        Duration duration = Duration.between(entryTime, exitTime);
        long minutes = duration.toMinutes();

        if (minutes <= MINIMUM_MINUTES) {
            return MINIMUM_FEE;
        }

        BigDecimal hours = new BigDecimal(Math.ceil(minutes / 60.0));
        return HOURLY_RATE.multiply(hours)
                .setScale(2, RoundingMode.HALF_UP);
    }


    public BigDecimal calculateTotalAmount(LocalDateTime entryTime,
                                           LocalDateTime scheduledExit) {
        return calculateBasicPrice(entryTime, scheduledExit);

    }

    public BigDecimal calculateRenewalPrice(LocalDateTime currentExitTime,
                                            int additionalHours) {
        return HOURLY_RATE.multiply(new BigDecimal(additionalHours));
    }
}