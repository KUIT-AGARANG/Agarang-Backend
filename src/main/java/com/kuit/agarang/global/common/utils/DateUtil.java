package com.kuit.agarang.global.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

  public static LocalDate convertStringToLocalDate(String dateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    try {
      return LocalDate.parse(dateStr, formatter);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException();
    }
  }

  public static LocalDate findDate(LocalDate localDate, int days) {
    return localDate.plusDays(days);
  }

  public static String formatLocalDateTime(LocalDateTime localDateTime, String format) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return localDateTime.format(formatter);
  }

}
