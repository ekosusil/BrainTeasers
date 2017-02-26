package com.home24.formula;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public interface CommonFormula {
	
	public static int sumOfNaturalNumber(int upperBound) {
		return upperBound * (upperBound + 1) / 2;
	}
	
	public static <K, V> Map<K, V> findCommonElement(Map<K, V> first, Map<K, V> second) {
		if (first.size() + second.size() == 0)
			return new LinkedHashMap<>();
		if (first.size() < second.size())
			return findCommonElement(second, first);

		Map<K, V> bigger = new TreeMap<K, V>(first);
		// remove un-matching key-value pair
		bigger.entrySet().retainAll(second.entrySet());
		return bigger;
	}

}
