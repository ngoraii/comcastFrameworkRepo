package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJSONFile(String key) throws FileNotFoundException, IOException, ParseException {
		//Parse JSON File into java object by JSONParser class
		FileReader file = new FileReader("./configAppData/commondata.json");
		JSONParser jp=new JSONParser();
		Object obj=jp.parse(file);
		
		//Convert java object into JSON Object by downcasting
		JSONObject map=(JSONObject)obj;//all data will be loaded as HashMap
		
		//Read or fetch the required value by giving key
		String value=(String)map.get(key);
		
		//Return the value for given key
		return value;
	}

}
