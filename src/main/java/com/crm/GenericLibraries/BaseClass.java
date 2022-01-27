package com.crm.GenericLibraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;

public class BaseClass {
	
	//Object creation for utility
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	
	public WebDriver driver;
	public static WebDriver sDriver = null;
	
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	
	public void configBS() {
		
		System.out.println("***************connect to DATABASE*****************");
	}
	
	//@Parameters("browser")
	@BeforeClass(groups = {"smokeTest","regressionTest"})
	
	public void configBC() throws Throwable {
								//how will you pass data from suite xml file to your test script
												//chrome - suite xml file = testNG
												//firefox - suite xml file
		//read common data from property files
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		
		//launch the browser
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else
			System.out.println("Invalid Browser");
		
		System.out.println("**************Launch the browser******************");
		wLib.waitForPageLoad(driver);
		wLib.maximizeWindow(driver);
		
		driver.get(URL);
		sDriver = driver;
				
	}
	
	@BeforeMethod(groups = {"smokeTest","regressionTest"})
	
	public void configBM() throws Throwable {
		
		
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//Login
		Login lp = new Login(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		System.out.println("***********Logged in to the application***************");
	}
	
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	
	public void configAM() {
		
		//Logout
		Home hp = new Home(driver);
		hp.logout();
		
		System.out.println("*************Logged out of the application****************");
	}
	
	@AfterClass(groups = {"smokeTest","regressionTest"})
	
	public void configAC() {
		
		//close the browser
		driver.quit();
		
		System.out.println("*****************Close the browser**********************");
	}
	
	@AfterSuite(groups = {"smokeTest","regressionTest"})
	
	public void configAS() {
		
		System.out.println("****************close the DATABASE********************");
	}
}
