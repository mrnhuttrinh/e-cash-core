package com.ecash.ecashcore.util;

public class StringUtils {

	public static boolean isNullOrEmpty(String value) {
		if(null == value) {
			return true;
		}
		
		return value.trim().equals("");
	}
}
