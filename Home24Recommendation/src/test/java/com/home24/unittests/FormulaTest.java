package com.home24.unittests;

import static com.home24.formula.CommonFormula.*;
import static com.home24.formula.RecommendationFormula.*;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FormulaTest {

	

	@Test
	public void sumOfNaturalNumberTest() {
		for (int test = 0; test <= 20; test++) {
			int sum = 0;
			for (int a = 0; a <= test; a++) {
				sum+=a;
			}
			assertTrue(sum == sumOfNaturalNumber(test));
		}
	}

	@Test
	public void commonEltTest() {
		Map<Integer,String> firstSet=new HashMap<>();
		firstSet.put(1, "ONE");
		firstSet.put(2, "TWO");
		firstSet.put(3, "THREE");
		firstSet.put(4, "FOUR");

		Map<Integer,String> secondSet=new HashMap<>();
		secondSet.put(3, "THREE");
		secondSet.put(4, "FOUR");
		secondSet.put(5, "FIVE");
		secondSet.put(6, "SIX");
		
		
		Map<Integer,String> thirdSet=new HashMap<>();
		thirdSet.put(3, "THREE");
		thirdSet.put(4, "FOUR");
		
		Map<Integer,String> commonElt=findCommonElement(firstSet,secondSet);
		assertTrue(thirdSet.equals(commonElt));	
	}
	
	@Test
	public void matchingScoreTest(){
		Map<Integer,String> firstSet=new HashMap<>();
		firstSet.put(1, "ONE");
		firstSet.put(2, "TWO");
		firstSet.put(3, "THREE");
		firstSet.put(4, "FOUR");

		Map<Integer,String> secondSet=new HashMap<>();
		secondSet.put(3, "THREE");
		secondSet.put(4, "FOUR");
		secondSet.put(5, "FIVE");
		secondSet.put(6, "SIX");
		
		Map<Integer,String> commonElt=findCommonElement(firstSet,secondSet);
		double matchingScore = matchingScore(firstSet,secondSet,commonElt);
		System.out.println("matching score "+matchingScore);
		assertTrue(matchingScore == 0.25);
	}
	
	@Test
	public void ordinalScoreTest(){
		Map<Integer,String> firstSet=new HashMap<>();
		firstSet.put(1, "ONE");
		firstSet.put(2, "TWO");
		firstSet.put(3, "THREE");
		firstSet.put(4, "FOUR");

		Map<Integer,String> secondSet=new HashMap<>();
		secondSet.put(3, "THREE");
		secondSet.put(4, "FOUR");
		secondSet.put(5, "FIVE");
		secondSet.put(6, "SIX");
		
		Map<Integer,String> commonElt=findCommonElement(firstSet,secondSet);
		long ordinalScore =Math.round(ordinalScore(firstSet,secondSet,commonElt)*100);
		System.out.println("ordinal score "+ordinalScore);
		//15%
		assertTrue(ordinalScore  == 15);
	}


}
