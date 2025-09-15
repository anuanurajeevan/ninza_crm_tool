package com.ninza.crm.generic.webdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random rm = new Random(); //create random class object
      	int randomNumber = rm.nextInt(1000);
      	return randomNumber;
	}
	
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim	 = new SimpleDateFormat("dd-MM-yy");
		String currentDate = sim.format(date);
		return currentDate;
	}
	
	public String getRequiredDate(int expectedDays) {
		Date date = new Date();
		SimpleDateFormat sim	 = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		
		Calendar cal = sim.getCalendar();
		
		cal.add(Calendar.DAY_OF_MONTH, expectedDays);
		String expectedDate = sim.format(cal.getTime());
		return expectedDate;
	}

}
