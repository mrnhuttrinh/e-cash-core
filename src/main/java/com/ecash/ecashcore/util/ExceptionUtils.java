package com.ecash.ecashcore.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

  public static String stackTraceToString(Throwable ex) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    ex.printStackTrace(pw);
    String result = sw.toString();
    pw.close();

    return result;
  }
}
