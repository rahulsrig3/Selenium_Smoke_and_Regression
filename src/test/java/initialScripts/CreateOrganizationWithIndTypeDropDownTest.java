package initialScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibraries.ExcelUtility;
import com.crm.GenericLibraries.FileUtility;
import com.crm.GenericLibraries.JavaUtility;
import com.crm.GenericLibraries.WebDriverUtility;

public class CreateOrganizationWithIndTypeDropDownTest {

	public static void main(String[] args) throws Throwable {
		
		JavaUtility jLib = new JavaUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//read data from property file
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
				
				
		//read data from Excel sheet
		String orgName = eLib.getDataFromExcel("Organization", 7, 2) + jLib.getRandomNumber();
		String indType = eLib.getDataFromExcel("Organization", 7, 3);
		String type = eLib.getDataFromExcel("Organization", 7, 4);
		
		//launch the browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Invalid Browser");
		}
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		
		//login to application
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//navigate to organization and create organization with mandatory fields
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		wLib.select(driver.findElement(By.name("industry")), indType);
		
		wLib.select(driver.findElement(By.name("accounttype")), type);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		Thread.sleep(3000);
		
		wLib.mouseHover(driver, driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")));
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		
		Thread.sleep(2000);
		driver.close();

	}

}
