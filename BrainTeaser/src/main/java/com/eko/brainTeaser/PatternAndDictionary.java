package com.eko.brainTeaser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PatternAndDictionary {
	// source https://www.careercup.com/question?id=5704645247762432
	public static void main(String args[]) {
		String pattern = "acc";
		String dictionary[] = { "cdf", "too", "hgfdt", "paa" };
	
		findMatchingPattern(pattern, dictionary).forEach(System.out::println);
	}

	public static List<String> findMatchingPattern(String pattern, String dictionary[]) {
		int patternLength = pattern.length();
		long uniqueLength = getUniqueChars(pattern);

		return Arrays.stream(dictionary)
				.filter(string -> string.length() == patternLength)
				.filter(string -> getUniqueChars(string) == uniqueLength)
				.collect(Collectors.toList());
	}

	private static long getUniqueChars(String string) {

		return string.chars().distinct().count();
	}
}
