package br.com.webfluxdemo.util;

import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {}

    public static final DateTimeFormatter ddMMyyyy_WITH_TRACE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public static final DateTimeFormatter ddMMyyyy_WHIT_STRIPE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
}
