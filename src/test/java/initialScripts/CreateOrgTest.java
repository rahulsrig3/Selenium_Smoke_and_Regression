package initialScripts;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CreateOrgTest
{

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException
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
		
		FileInputStream fil = new FileInputStream("./excel/ExcelData01.xlsx");
		
		Workbook wb = WorkbookFactory.create(fil);
		
		String BROWSER = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		String URL = wb.getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue();
		String USERNAME = wb.getSheet("Sheet1").getRow(3).getCell(1).getStringCellValue();
		String PASSWORD = wb.getSheet("Sheet1").getRow(4).getCell(1).getStringCellValue();
		
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
		
		String ORGANIZATION_NAME = wb.getSheet("Sheet1").getRow(5).getCell(1).getStringCellValue();
		String WEBSITE = wb.getSheet("Sheet1").getRow(6).getCell(1).getStringCellValue();
		String PHONE = wb.getSheet("Sheet1").getRow(7).getCell(1).getStringCellValue();
		//String TICKET_SYMBOL = 
		String FAX = wb.getSheet("Sheet1").getRow(8).getCell(1).getStringCellValue();
		String EMPLOYEES = wb.getSheet("Sheet1").getRow(9).getCell(1).getStringCellValue();
		String EMAIL = wb.getSheet("Sheet1").getRow(10).getCell(1).getStringCellValue();
		String INDUSTRY = wb.getSheet("Sheet1").getRow(11).getCell(1).getStringCellValue();
		
		String BILLING_ADDRESS = wb.getSheet("Sheet1").getRow(13).getCell(1).getStringCellValue();
		String BILLING_PO_BOX = wb.getSheet("Sheet1").getRow(14).getCell(1).getStringCellValue();
		String BILLING_CITY = wb.getSheet("Sheet1").getRow(15).getCell(1).getStringCellValue();
		String BILLING_STATE = wb.getSheet("Sheet1").getRow(16).getCell(1).getStringCellValue();
		String BILLING_POSTAL_CODE = wb.getSheet("Sheet1").getRow(17).getCell(1).getStringCellValue();
		String BILLING_COUNTRY = wb.getSheet("Sheet1").getRow(18).getCell(1).getStringCellValue();
		
		
		driver.findElement(By.xpath("//input[@name=\"accountname\"]")).sendKeys(ORGANIZATION_NAME);
		driver.findElement(By.name("website")).sendKeys(WEBSITE);
		driver.findElement(By.name("phone")).sendKeys(PHONE);
		//driver.findElement(By.name("tickersymbol")).sendKeys(TICKET_SYMBOL);
		driver.findElement(By.name("fax")).sendKeys(FAX);
		driver.findElement(By.name("employees")).sendKeys(EMPLOYEES);
		driver.findElement(By.name("email1")).sendKeys(EMAIL);
		
		WebElement ele = driver.findElement(By.name("industry"));
		Select s = new Select(ele);
		s.selectByValue(INDUSTRY);
		Thread.sleep(2000);
		
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
	
