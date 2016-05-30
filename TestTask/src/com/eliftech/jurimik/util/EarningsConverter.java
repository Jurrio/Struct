package com.eliftech.jurimik.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eliftech.jurimik.exception.IllegalFormatEarningsException;

public class EarningsConverter {
	
	public static long get(String strEarn) throws IllegalFormatEarningsException {
		long result = 0;
		if (checkWithRegex(strEarn)) {
			result = getWithK(strEarn);
		} else {
			throw new IllegalFormatEarningsException("Format of String: \"" + strEarn + "\" not allow parsing it to long");
		}
		return result;
	}
	
	private static long getWithK(String strEarn) {
		long l;
		try {
			l = Long.parseLong(strEarn);
		} catch (NumberFormatException nfe) {
			char c = strEarn.charAt(strEarn.length() - 1);
			l = Long.parseLong(strEarn.substring(0, strEarn.length() - 1));
			switch (c) {
				case 'K':
				case 'k':
					l = l * 1000;
					break;
				case 'M':
				case 'm':
					l = l * 1000000;
				default:
					break;
			}
		}
		return l;
	}

	private static boolean checkWithRegex(String strEarn) {
		Pattern p = Pattern.compile("^\\d+((K)|(k)|(M)|(m))?{1}");
		Matcher m = p.matcher(strEarn);
		return m.matches();
	}
	
	public static void main(String[] args) throws IllegalFormatEarningsException {
		System.out.println(get("100"));
		System.out.println(get("100K"));
		System.out.println(get("100M"));
		System.out.println(get("100m"));
		System.out.println(get("1w00K"));
		System.out.println(get("100aas"));
		System.out.println(get("100E"));	
	}

}
