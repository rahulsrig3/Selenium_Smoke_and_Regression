package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibraries.ExcelUtility;
import com.crm.GenericLibraries.FileUtility;
import com.crm.GenericLibraries.JavaUtility;
import com.crm.GenericLibraries.WebDriverUtility;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.Organizations;

public class PracticeSample {

	public static void main(String[] args) throws Throwable {
		
		//object creation for utility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//read common data from properties file
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read test data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
		String orgName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		
		//launch the browser
				WebDriver driver = null;
				if (BROWSER.equalsIgnoreCase("chrome")) {
					driver = new ChromeDriver();	
				}
				else if (BROWSER.equalsIgnoreCase("firefox")) {
					driver = new FirefoxDriver();
				}
				else {
					System.out.println("Invalid browser");
				}
						
				driver.get(URL);
				
		//STEP 1: login to the application
				Login ln = new Login(driver);
				ln.loginToApp(USERNAME, PASSWORD);
				
				//STEP 2: Navigate to Organization page
				Home hp = new Home(driver);
				hp.getOrgLink().click();
				
				//STEP 3: Navigate to create new organization page
				Organizations op = new Organizations(driver);
				op.getCreateNewOrgImg().click();
				
				//STEP 4: Creating new Organization
				CreatingNewOrganization cno = new CreatingNewOrganization(driver);
				cno.createOrg(orgName);
				
		

	}

}
