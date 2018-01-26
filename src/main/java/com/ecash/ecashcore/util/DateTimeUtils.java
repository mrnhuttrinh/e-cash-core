package com.ecash.ecashcore.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateTimeUtils
{
  private static final Logger LOG = LoggerFactory.getLogger(DateTimeUtils.class);
  public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

  public static final String REPORT_PARAM_FORMAT = "MM/dd/yyyy";

  public static final String DEFAULT_FORMAT_SHORT_DATE = "yyyyMMdd";

  public static DateTime parseDate(String dateString, String datePatternParam,
      DateTimeZone timeZone)
  {
    DateTime dt = null;
    String datePattern = datePatternParam;
    try
    {
      if (StringUtils.isNullOrEmpty(datePattern))
      {
        datePattern = DEFAULT_FORMAT;
      }

      if (!StringUtils.isNullOrEmpty(dateString))
      {
        DateTimeFormatter dtf = DateTimeFormat.forPattern(datePattern);

        if (timeZone != null)
        {
          dt = dtf.withZone(timeZone).parseDateTime(dateString);
        }
        else
        {
          dt = dtf.parseDateTime(dateString);
        }
      }
    }
    catch (Exception e)
    {
      LOG.error(e.getMessage(), e);
    }

    return dt;
  }

  public static String toDefaultFormatString(Date date)
  {
    if (date == null)
    {
      return null;
    }
    SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_FORMAT);
    return formatter.format(date);
  }

  public static String datetoString(Date date, final String inputFormat)
  {
    String dateFormat = inputFormat;
    if (StringUtils.isNullOrEmpty(dateFormat))
    {
      dateFormat = DEFAULT_FORMAT;
    }
    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
    return formatter.format(date);
  }

  public static Date convertToStartOfDay(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return cal.getTime();
  }

  public static Date convertToEndOfDay(Date date)
  {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);
    return new Date(cal.getTime().getTime() + TimeUnit.DAYS.toMillis(1));
  }
}
