package com.home24.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.home24.engine.RecommendationEngine;
import com.home24.formula.RecommendationFormula;
import com.home24.io.JsonParser;
import com.home24.utils.Constants;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String args[]){
		Properties properties = new Properties();
		try {
			if (args.length>0){
				
				properties.load(Main.class.getResourceAsStream("/recommendation.properties"));
				String dataToSearch[]=args[0].split(Constants.SEPARATOR);
				logger.info(String.format("Data to search is %s",dataToSearch));
				System.out.println(String.format("Data to search is %s",dataToSearch));

				
				
				String strLimit=args.length > 1 ? args[1] : properties.getProperty(Constants.NUMBER_OF_SIMILAR_ELEMENTS,"10");
				Long limit = StringUtils.isNumeric(strLimit) ? Long.parseLong(strLimit) :10;
				logger.info("Limit on matching elements "+limit);
				System.out.println("Limit on matching elements "+limit);
				
				if(args.length>2){
					logger.info("Using speified data source "+args[2]);
					System.out.println("Using speified data source "+args[2]);

				}
				else{
					logger.info("Using default data source on jar");
					System.out.println("Using default data source on jar");
					
				}
				InputStream is = args.length >2 ? new FileInputStream(new File(args[2])):Main.class.getResourceAsStream(Constants.DEFAULT_DATA_SOURCE);
			
				Map<String,Map<String,Map>> data =JsonParser.parseJsonFromFile(is);
				
				for(String curElt : dataToSearch){
					RecommendationEngine.getNSimilarElement(curElt, data, limit).
						forEach((key,val) ->{
							System.out.println("Given data "+curElt+" is similar to "+key+" with weight "+val);
							logger.info("Given data "+curElt+" is similar to "+key+" with weight "+val);
					});
				}
			}
			else{
				
			}
			
		} catch (IOException e) {
			logger.info(String.format("Problem reading property file %s",e.getMessage()));
		}
	}


}
