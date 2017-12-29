package com.ecash.ecashcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtils {

  public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

  public static final String DEFAULT_FORMAT_SHORT_DATE = "yyyyMMdd";

  public static String toDefaultFormatString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
    return formatter.format(date);
  }

  public static String datetoString(Date date, final String inputFormat) {
    String dateFormat = inputFormat;
    if (StringUtils.isNullOrEmpty(dateFormat)) {
      dateFormat = DEFAULT_FORMAT;
    }
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
    return formatter.format(date);
  }
}
