package com.ecash.ecashcore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtils {

  public static final String DEFAULT_FORMAT = "MM/dd/yyyy";

  public static String toDefaultFormatString(Date date) {
    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
    return formatter.format(date);
  }
}
