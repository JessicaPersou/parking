package br.com.postech.parking.comon;

import br.com.postech.parking.exception.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class DateTimeFormatterUtil {

    public static Integer calculateDurationInHours(LocalDateTime entry, LocalDateTime exit) {
        if (entry == null || exit == null) {
            log.info("entry time or exit time is null, or invalid");
            throw new InvalidFormatException("entry or exit time is null");
        }
        Duration duration = Duration.between(entry, exit);
        return (int) duration.toHours();
    }

}
