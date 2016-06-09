package com.eliftech.jurimik.util;

import com.eliftech.jurimik.exception.SearchEmptyException;
import com.eliftech.jurimik.exception.SearchManyParamException;

public class ParamUtils {
	
	public static boolean isBlank(String str){
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String checkSearchValue(String parameter) throws SearchManyParamException, SearchEmptyException  {
		if (parameter.split(" ").length > 1) {
			throw new SearchManyParamException("Unfortunally, our service is very lazy and does not seek more than one word.");
		}
		if (parameter.trim().isEmpty()) {
			throw new SearchEmptyException("You tried use search with empty query. Do not do it this way! ;-)");
		}
		return parameter.trim();
	}
	
}
