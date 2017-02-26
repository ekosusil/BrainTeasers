package com.home24.formula;

import static com.home24.formula.CommonFormula.sumOfNaturalNumber;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface RecommendationFormula {
	// formula matching
	public static <K, V> double matchingScore(Map<K, V> first, Map<K, V> second, Map<K, V> matching) {

		if (first.size() + second.size() == 0)
			return 0;
		if (matching.size() == 0)
			return 0;

		double result = (double) matching.size() / (double) (first.size() + second.size());
		return result;
	}

	// ordinal matching
	public static <K, V> double ordinalScore(Map<K, V> original,Map<K, V> second, Map<K, V> matching) {

		// if not in correct order, invert
		if (original.size() == 0)
			return 0;
		if (matching.size() == 0)
			return 0;
		List<K> matchingKeys = new LinkedList<>(matching.keySet());
		List<K> originalKeys = new LinkedList<>(original.keySet());
		Collections.sort(matchingKeys, Collections.reverseOrder());
		Collections.sort(originalKeys, Collections.reverseOrder());
		return matchingKeys.stream().map(item -> originalKeys.indexOf(item))
				.mapToDouble(idx -> (double) (idx + 1) / (double) sumOfNaturalNumber(originalKeys.size()))
				.sum() / 2;

	}




}
