package com.crm.GenericLibraries;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * this class provides generic methods to read data from property file
 * @author Rahul Srivastava
 *
 */
public class FileUtility {
	
	public String readDataFromPropertyFile(String key) throws Throwable
	{
		FileInputStream fis = new FileInputStream(IPathConstants.FilePath);
		Properties pro = new Properties();
		pro.load(fis);
		String value = pro.getProperty(key);
		return value;	
	}

}
