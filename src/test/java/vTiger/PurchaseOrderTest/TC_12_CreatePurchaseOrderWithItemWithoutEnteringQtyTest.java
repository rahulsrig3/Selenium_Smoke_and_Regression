package vTiger.PurchaseOrderTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.crm.GenericLibraries.ExcelUtility;
import com.crm.GenericLibraries.FileUtility;
import com.crm.GenericLibraries.JavaUtility;
import com.crm.GenericLibraries.WebDriverUtility;

public class TC_12_CreatePurchaseOrderWithItemWithoutEnteringQtyTest {

	public static void main(String[] args) throws Throwable
	{
		JavaUtility jLib = new JavaUtility();
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		
		//read data from property file
		String BROWSER = fLib.readDataFromPropertyFile("browser");
		String URL = fLib.readDataFromPropertyFile("url");
		String USERNAME = fLib.readDataFromPropertyFile("username");
		String PASSWORD = fLib.readDataFromPropertyFile("password");
		
		//read data from excel file
		String subject = eLib.getDataFromExcel("Purchase", 1, 2) + jLib.getRandomNumber();
		String VendorName = eLib.getDataFromExcel("Purchase", 1, 3);
		String Status = eLib.getDataFromExcel("Purchase", 1, 4);
		String BillingAddress = eLib.getDataFromExcel("Purchase", 1, 5);
		
		WebDriver driver = null;
		
		if(BROWSER.equals("chrome"))
			driver = new ChromeDriver();
		else if(BROWSER.equals("firefox"))
			driver = new FirefoxDriver();
		
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver);
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		wLib.mouseHover(driver, driver.findElement(By.xpath("//a[text()=\"More\"]")));
		driver.findElement(By.name("Purchase Order")).click();
		driver.findElement(By.xpath("//img[@alt=\"Create Purchase Order...\"]")).click();
		wLib.waitForPageLoad(driver);
		
		driver.findElement(By.name("subject")).sendKeys(subject);
		driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
		
		wLib.switchToWindow(driver, "Vendors");
		driver.findElement(By.linkText(VendorName)).click();
		wLib.switchToWindow(driver, "Purchase");
		
		wLib.select(driver.findElement(By.name("postatus")), Status);
		
		driver.findElement(By.name("bill_street")).sendKeys(BillingAddress);
		driver.findElement(By.xpath("//input[@onclick=\"return copyAddressRight(EditView)\"]")).click();
		
		driver.findElement(By.id("searchIcon1")).click();
		wLib.switchToWindow(driver, "Products");
		Thread.sleep(2000);
		driver.findElement(By.linkText("Vtiger 50 Users Pack")).click();
		wLib.switchToWindow(driver, "Purchase");
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		wLib.acceptAlert(driver);
		
		Thread.sleep(2000);
		
		wLib.mouseHover(driver, driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")));
		driver.findElement(By.xpath("//a[text()=\"Sign Out\"]")).click();
		
		Thread.sleep(2000);
		driver.close();
		
		
		/*&driver.findElement(By.name("subject")).sendKeys(subject);
		
		WebElement ele = driver.findElement(By.name("postatus"));
		Select s = new Select(ele);
	    s.selectByValue(status);
		
		driver.findElement(By.xpath("//img[@title=\"Select\"]")).click();
		
		Set<String> windows01 = driver.getWindowHandles();
		Iterator<String> iterator01 = windows01.iterator();
		String parentWindow01 = iterator01.next();
		String childwindow01 = iterator01.next();
		driver.switchTo().window(childwindow01);
		driver.findElement(By.xpath("//a[text()=\"Mary\"]")).click();
		driver.switchTo().window(parentWindow01);
		
		driver.findElement(By.xpath("//img[@id=\"searchIcon1\"]")).click();
		
		Thread.sleep(3000);
		
		Set<String> windows02 = driver.getWindowHandles();
		Iterator<String> iterator02 = windows02.iterator();
		String parentWindow02 = iterator02.next();
		String childwindow02 = iterator02.next();
		driver.switchTo().window(childwindow02);
		driver.findElement(By.xpath("//input[@value=\"56\"]")).click();
		driver.findElement(By.xpath("//input[@value=\"Select Products\"]")).click();
		driver.switchTo().window(parentWindow02);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		Thread.sleep(5000);
		Alert al = driver.switchTo().alert();
		String ab = al.getText();
		System.out.println(ab);
		
		System.out.println(pro.getProperty("alert_msg"));
		Assert.assertEquals(ab,pro.getProperty("alert_msg") );
		al.accept();  */
		
		
		
		

	}

}
