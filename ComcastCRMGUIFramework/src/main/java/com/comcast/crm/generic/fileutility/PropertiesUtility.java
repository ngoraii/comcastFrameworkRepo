package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	public String getDataFromPropertiesFile(String key) throws IOException {
		
		//Fetch the properties file and get java representation object
		FileInputStream fis=new FileInputStream("./configAppData/commondata.properties");
		
		//create properties class object and load properties file all the keys and values to it
		Properties pObj=new Properties();
		pObj.load(fis);//all data will be loaded as HashMap
		
		//Read the data using getProperties("Key")
		String value=pObj.getProperty(key);
		
		//Return the value for the given Key
		return value;
		
	}

}
