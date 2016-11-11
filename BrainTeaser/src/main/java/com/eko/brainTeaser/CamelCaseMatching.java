package com.eko.brainTeaser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CamelCaseMatching {
	// source https://www.careercup.com/question?id=5660887265312768

	public static void main(String args[]) {

		List<String> data = Arrays.asList("H", "HW", "Ho", "HeWorM");
		List<String> details = Arrays.asList("HelloMars", "HelloWorld", "HelloWorldMars", "HiHo");
		details.forEach(str -> {
			data.forEach(curCamCase -> {
				System.out.println(" cam case " + curCamCase + " compared to string " + str + " is "
						+ isMatchCamelCase(curCamCase, str));
			});
		});

		System.out.println("match?" + isMatchCamelCase("HelWorM", "HelloWorld"));
	}

	public static boolean isMatchCamelCase(String camelCase, String word) {
		if (camelCase.length() > 0 && word.length() == 0) {
			return false;
		}
		if (camelCase.length() == 0) {
			return true;
		}
		if (Character.isUpperCase(camelCase.charAt(0))) {
			if (Character.isUpperCase(word.charAt(0))) {
				return camelCase.charAt(0) == word.charAt(0)
						&& isMatchCamelCase(camelCase.substring(1), word.substring(1));
			} else {
				return isMatchCamelCase(camelCase, word.substring(1));
			}
		} else if (!Character.isUpperCase(camelCase.charAt(0))) {
			return camelCase.charAt(0) == word.charAt(0) && isMatchCamelCase(camelCase.substring(1), word.substring(1));
		}

		return true;
	}

}
