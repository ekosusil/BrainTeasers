package com.eko.brainTeaser;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PairMergingAndSorting {

	// source https://www.careercup.com/question?id=5145370309951488
	public static void main(String args[]) {
		// pair = id, weight
		AbstractMap.SimpleEntry itemOne[] = { new AbstractMap.SimpleEntry<Integer, Integer>(2, 5),
				new AbstractMap.SimpleEntry<Integer, Integer>(5, 10),
				new AbstractMap.SimpleEntry<Integer, Integer>(1, 10),
				new AbstractMap.SimpleEntry<Integer, Integer>(4, 12) };
		AbstractMap.SimpleEntry itemTwo[] = { new AbstractMap.SimpleEntry<Integer, Integer>(3, 7),
				new AbstractMap.SimpleEntry<Integer, Integer>(2, 10),
				new AbstractMap.SimpleEntry<Integer, Integer>(1, 10) };
		AbstractMap.SimpleEntry<Integer, Integer> merged[] = mergeById(itemOne, itemTwo);
		Arrays.stream(merged).forEach(System.out::println);
	}

	@SuppressWarnings("unchecked")
	public static AbstractMap.SimpleEntry<Integer, Integer>[] mergeById(
			AbstractMap.SimpleEntry<Integer, Integer>[] first, AbstractMap.SimpleEntry<Integer, Integer>[] second) {
		Map<Integer, Integer> reduced = Stream.concat(Arrays.stream(first), Arrays.stream(second)).collect(Collectors
				.groupingBy(AbstractMap.SimpleEntry::getKey, Collectors.reducing(0, AbstractMap.SimpleEntry::getValue, Integer::sum)));

		AbstractMap.SimpleEntry<Integer, Integer>[] pairs = (AbstractMap.SimpleEntry<Integer, Integer>[]) reduced
				.entrySet().stream()
				.map(entry -> new AbstractMap.SimpleEntry<Integer, Integer>(entry.getKey(), entry.getValue()))
				.sorted((p1, p2) -> p1.getValue().compareTo(p2.getValue())).toArray(AbstractMap.SimpleEntry[]::new);

		return pairs;
	}
}
