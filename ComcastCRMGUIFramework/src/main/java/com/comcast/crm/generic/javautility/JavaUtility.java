package com.comcast.crm.generic.javautility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random r=new Random();
		int randomNum = r.nextInt(2000);
		return randomNum;
	}
	public String getSystemDateYYYYMMDD() {
		Date dateobj=new Date();
		SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpDate.format(dateobj);
		return date;
		
	}

public String getRequiredDateYYYYMMDD(int days) {
	SimpleDateFormat simpDate = new SimpleDateFormat("yyyy-MM-dd");
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.DAY_OF_MONTH, days);
	String reqDate = simpDate.format(cal.getTime());
	return reqDate;
}

public String getSSTime() {
	Date date=new Date();
	String dateTime = date.toString().replace(" ", "_").replace(":","_");
	return dateTime;
}

}
