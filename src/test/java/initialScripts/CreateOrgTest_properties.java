package initialScripts;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrgTest_properties {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		/* Assignment:
		 * step1: login
		 * step2: navigate to organization module
		 * step3: click on organization button
		 * step4: enter all the details and create a organization
		 * step5: verify organization name in header of the message
		 * step6: logout 
		 * NOTE:
		 * 1. any data should not be hard coded 
		 * 2. test should be able to run in different browsers with minimal changes
		 * 3. test script data should get it from excel		*/
		
		FileInputStream fil = new FileInputStream("./data/commonData.properties");
		Properties pro = new Properties();
		pro.load(fil);
		
		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		
		WebDriver driver = null;
		
		if(BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if(BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		
		FileInputStream filex = new FileInputStream("./data/CreateOrganization.properties");
		pro.load(filex);
		
		String ORGANIZATION_NAME = pro.getProperty("organization_name");
		String WEBSITE = pro.getProperty("Website");
		String PHONE = pro.getProperty("Phone");
		//String TICKET_SYMBOL = pro.getProperty("Ticker");
		String FAX = pro.getProperty("Fax");
		String EMPLOYEES = pro.getProperty("Employees");
		String EMAIL = pro.getProperty("Email");
		
		String BILLING_ADDRESS = pro.getProperty("Billing_Address");
		String BILLING_PO_BOX = pro.getProperty("Billing_po_box");
		String BILLING_CITY = pro.getProperty("Billing_City");
		String BILLING_STATE = pro.getProperty("Billing_State");
		String BILLING_POSTAL_CODE = pro.getProperty("Billing_Postal_Code");
		String BILLING_COUNTRY = pro.getProperty("Billing_Country");
		
		
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(ORGANIZATION_NAME);
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		driver.findElement(By.name("phone")).sendKeys(PHONE);
		//driver.findElement(By.name("tickersymbol")).sendKeys(TICKET_SYMBOL);
		driver.findElement(By.name("fax")).sendKeys(FAX);
		driver.findElement(By.name("employees")).sendKeys(EMPLOYEES);
		driver.findElement(By.name("email1")).sendKeys(EMAIL);
		
		WebElement ele = driver.findElement(By.name("industry"));
		Select s = new Select(ele);
		s.selectByValue("Healthcare");
		Thread.sleep(2000);
		
		//ele = driver.findElement(By.name("assigned_user_id"));
		//s.selectByValue("1");
		
		driver.findElement(By.name("bill_street")).sendKeys(BILLING_ADDRESS);
		driver.findElement(By.name("bill_pobox")).sendKeys(BILLING_PO_BOX);
		driver.findElement(By.name("bill_city")).sendKeys(BILLING_CITY);
		driver.findElement(By.name("bill_state")).sendKeys(BILLING_STATE);
		driver.findElement(By.name("bill_code")).sendKeys(BILLING_POSTAL_CODE);
		driver.findElement(By.name("bill_country")).sendKeys(BILLING_COUNTRY);
		
		driver.findElement(By.xpath("//input[@onclick=\"return copyAddressRight(EditView)\"]")).click();
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		Thread.sleep(3000);
		
		String expectedTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		String actualTitle = driver.getTitle();
		
		if(expectedTitle.equals(actualTitle)) {
			System.out.println(actualTitle);
			System.out.println("new organization successfully created");
		}
		else {
			System.out.println(actualTitle);
			System.out.println("new organization not created");
		}
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"))).perform();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		
		Thread.sleep(5000);
		driver.close();

	}

}
