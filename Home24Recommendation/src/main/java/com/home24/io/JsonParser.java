package com.home24.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapLikeType;

public class JsonParser {

	private static Logger logger = LoggerFactory.getLogger(JsonParser.class);

	public static Map<String, Map<String, Map>> parseJsonFromFile(String filePath) {
		try {
			return parseJsonFromFile(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			logger.error(
					String.format("Problem when opening-json. FilePath = %s , Error = %s, ", filePath, e.getMessage()));
		}
		return  new HashMap<>();
	}
	
	
	public static Map<String, Map<String, Map>> parseJsonFromFile(InputStream is) {
		ObjectMapper mapper = new ObjectMapper();
		
		MapLikeType newType = mapper.getTypeFactory().constructMapLikeType(Map.class, String.class,
				LinkedHashMap.class);
		try {
			Map<String, Map<String, Map>> allData = mapper.readValue(is, newType);
			return allData;
		} catch (IOException e) {
			logger.error(
					String.format("Problem when parsing-json. FilePath = %s , Error = %s, ", is, e.getMessage()));
		}
		return new HashMap<>();
	}

}
