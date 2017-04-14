/**
 * @author Poorvisha
 *
 */
package com.assignment;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class MaxTempSpread {

private static final String defaultdDataPath = "data/monthly_temperature.json";

	public static void main(String[] args) {
	
		try {
			//To read the JSON data file from the mentioned path, parse it and fetch the days array.
			FileReader reader = new FileReader(defaultdDataPath);
		
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);		
			JSONArray days= (JSONArray) jsonObject.get("days");
			
			// Variables to hold the max temperature difference and the respective day.
			double maxTempDiff = 0.0;
			String maxTempDiffDay = "";
			
			//temporary variables to hold current day and temperature difference.
			JSONObject dayJson = null;			
			double currentDayTempDiff = 0.0;
			
			DecimalFormat df = new DecimalFormat("##.##");
			
			if(null != days && days.size() > 0){
				Iterator daysIterator = days.iterator();
				
				//to fetch the value of first day
				if(daysIterator.hasNext()){
					dayJson = (JSONObject) daysIterator.next();
					maxTempDiff = Double.parseDouble(dayJson.get("high").toString()) - Double.parseDouble(dayJson.get("low").toString());
					maxTempDiffDay = dayJson.get("day").toString();
				}
				
				//Linear search to find the maximum by comparing with difference for each element in the array [complexity O(n)]		
				while (daysIterator.hasNext()) {
					dayJson = (JSONObject) daysIterator.next();
					currentDayTempDiff = Double.parseDouble(dayJson.get("high").toString()) - Double.parseDouble(dayJson.get("low").toString());
					if(currentDayTempDiff > maxTempDiff){
						maxTempDiffDay = dayJson.get("day").toString();
						maxTempDiff = currentDayTempDiff;
					}			
				}
				
				String temp_month = jsonObject.get("month").toString();
				String temp_year = jsonObject.get("year").toString();
				System.out.println("The month and year are " + temp_month + "/" + temp_year);
				System.out.println("The maximum temperature spread was on day " + maxTempDiffDay + " and the difference was " + df.format(maxTempDiff));
				
			}else {
				System.out.println("There is no valid temperature data for any day in the file");
			}
	
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}

}


