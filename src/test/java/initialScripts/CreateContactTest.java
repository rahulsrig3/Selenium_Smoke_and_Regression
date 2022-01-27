package initialScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.GenericLibraries.ExcelUtility;
import com.crm.GenericLibraries.FileUtility;
import com.crm.GenericLibraries.JavaUtility;
import com.crm.comcast.objectrepositorylib.ContactInformation;
import com.crm.comcast.objectrepositorylib.Contacts;
import com.crm.comcast.objectrepositorylib.CreatingNewContact;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.Login;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable {
		
		//object creation for utility
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		
		//read common data from properties file
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read test data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
		
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
		
		//STEP 2: navigate to contact page
		Home hm = new Home(driver);
		hm.getContactLink().click();
		
		//STEP 3: navigate to create new contact page
		Contacts co = new Contacts(driver);
		co.getCreateNewContactImg().click();
		
		//STEP 4: create contact
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.createContact(lastName);
		
		//STEP 5: verify the contact details
		ContactInformation ci = new ContactInformation(driver);
		String actLastName = ci.getContactHeaderSuccMsg().getText();
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + "--> Contact last name is verified == Passed Script");
		}
		else
		{
			System.out.println(lastName + "--> Contact last name is not verified == Failed Script");
		}
		
		//STEP 6: logout
		hm.logout();
		
		//close the browser
		driver.quit();
		
	}

}
