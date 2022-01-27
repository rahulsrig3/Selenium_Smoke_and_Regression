package initialScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.GenericLibraries.ExcelUtility;
import com.crm.GenericLibraries.FileUtility;
import com.crm.GenericLibraries.JavaUtility;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;

public class CreateOrganizationTest {

@Test

	public void createOrganization() throws Throwable {
	
		//Object creation for utility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		
		//read common data from property files
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read test data from excel file
		String orgName = eLib.getDataFromExcel("Organization", 1, 2) + jLib.getRandomNumber();
		
		//launch the browser
		WebDriver driver = null;
		if(BROWSER.equalsIgnoreCase("chrome"))
			driver = new ChromeDriver();
		else if(BROWSER.equalsIgnoreCase("firefox"))
			driver = new FirefoxDriver();
		else
			System.out.println("Invalid Browser");
		
		driver.get(URL);
		
		//STEP 1: Login
		Login lp = new Login(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		
		//STEP 2: Navigate to Organization page
		Home hp = new Home(driver);
		hp.getOrgLink().click();
		
		//STEP 3: Navigate to create new organization page
		Organizations op = new Organizations(driver);
		op.getCreateNewOrgImg().click();
		
		//STEP 4: Creating new Organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg(orgName);
		
		//STEP 5: Verify the organization details
		OrganizationInformation oi = new OrganizationInformation(driver);
		String actOrgSucMsg = oi.getOrgHeaderSucMsg().getText();
		
		if (actOrgSucMsg.contains(orgName)) {
			System.out.println("organization created successfully == Passed script");
		}
		else {
			System.out.println("organization not created successfully == Failed script");
		}
		
		//STEP 6: Logout
		hp.logout();
		
		//close the browser
		driver.quit();
	}
}
		