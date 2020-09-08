package com.example.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateHelper {

    private static final String DATE_TIME_FORMAT_yyyy_mm_dd_hh_mm_ss = "yyyy-MM-dd HH:mm:ss:ms ";
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder().appendPattern(DATE_TIME_FORMAT_yyyy_mm_dd_hh_mm_ss).toFormatter();

    public static String getFormattedLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DATE_FORMATTER);
    }
}
