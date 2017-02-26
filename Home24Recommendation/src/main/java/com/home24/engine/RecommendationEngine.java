package com.home24.engine;

import static com.home24.formula.RecommendationFormula.*;
import static com.home24.formula.CommonFormula.*;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecommendationEngine {
	

	static BinaryOperator<Map<String, Map>> findIntersection = (first, second) -> findCommonElement(first, second);
	static BiFunction<Map<String, Map>, BinaryOperator<Map<String, Map>>, Function<Map<String, Map>, Double>> parsing = (
			jsonToFind, insertionFunction) -> jsonToIterate -> {
				Map<String, Map> intersectionElt = insertionFunction.apply(jsonToFind, jsonToIterate);
				return matchingScore(jsonToFind, jsonToIterate, intersectionElt)
						+ ordinalScore(jsonToFind, jsonToIterate, intersectionElt);
			};

	public static Map<String, Double> getNSimilarElement(String dataToSearch, Map<String, Map<String, Map>> allData,
			Long limit) {

		if (allData.containsKey(dataToSearch)) {
			Map<String, Double> data = allData.entrySet().stream()
					.map(entry -> new AbstractMap.SimpleEntry<String, Double>(entry.getKey(),
							parsing.apply(allData.get(dataToSearch), findIntersection).apply(entry.getValue())))
					.sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
					.limit(limit)
					.collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
			return data;
		}
		return new HashMap<>();
	}
	
	public static Map<String, Double> getAllSimilarElement(String dataToSearch, Map<String, Map<String, Map>> allData) {

		if (allData.containsKey(dataToSearch)) {
			Map<String, Double> data = allData.entrySet().stream()
					.map(entry -> new AbstractMap.SimpleEntry<String, Double>(entry.getKey(),
							parsing.apply(allData.get(dataToSearch), findIntersection).apply(entry.getValue())))
					.filter(entry -> entry.getValue().compareTo(0.7) > 0)
					.sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
					.collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue));
			return data;
		}
		return new HashMap<>();
	}
}
