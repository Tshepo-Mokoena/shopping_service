package com.shopping_service.util;

import org.apache.commons.lang3.RandomStringUtils;

public class Util {
	
	public static String generateUniqueNumericUUId() {
		return RandomStringUtils.randomNumeric(16);
	}
	
	public static String generateUniqueAlphaNumericUUId() {
		return RandomStringUtils.randomAlphanumeric(16);
	}

}
