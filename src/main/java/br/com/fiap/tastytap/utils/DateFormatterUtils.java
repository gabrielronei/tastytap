package br.com.fiap.tastytap.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtils {

    public static String format(LocalDateTime somewhereInTime) {
        if(somewhereInTime == null) return "-";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dateTimeFormatter.format(somewhereInTime);
    }

}
