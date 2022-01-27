package com.crm.GenericLibraries;


import java.util.Date;
import java.util.Random;

/**
 * This class contains generic methods wrt java
 * @author Rahul Srivastava
 *
 */

public class JavaUtility  //always write non-static methods in generic methods and provide description
{
	/**
	 * This method will generate a random number and returns it to the caller
	 * @return
	 */
	public int getRandomNumber()
	{
		
		Random ran = new Random();
		int random = ran.nextInt(1000);
		return random;	
	}
	
	/**
	 * This method will return current date to the caller
	 * @return
	 */
	
	public String getCurrentDate()
	{
		Date date = new Date();
		String currentDate = date.toString();
		return currentDate;
	}
	
	/**
	 * This method will return date in specified format to the caller
	 * @return
	 */
	
	public String getFinalDateFormat()
	{
		Date ndate = new Date();
		String d = ndate.toString();
		String[] dsp = d.split(" ");
		String yyyy = dsp[5];
		String dd = dsp[2];
		String mm = dsp[1];
		String today = yyyy+"-"+mm+"-"+dd;
		
		return today;	
	}	
}
