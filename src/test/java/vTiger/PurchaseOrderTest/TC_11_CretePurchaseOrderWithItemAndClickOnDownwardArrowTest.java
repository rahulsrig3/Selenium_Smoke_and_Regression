package vTiger.PurchaseOrderTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC_11_CretePurchaseOrderWithItemAndClickOnDownwardArrowTest {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		FileInputStream file = new FileInputStream("./data/commonData.properties");
		Properties pro = new Properties();
		pro.load(file);
		
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
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//a[text()=\"More\"]"))).perform();
		Thread.sleep(2000);
		driver.findElement(By.name("Services")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Service...\"]")).click();
		
		file = new FileInputStream("./data/TC_11.properties");
		pro.load(file);
		
		String service_name = pro.getProperty("service_name");
		String usage_unit = pro.getProperty("usage_unit");
		String no_of_units = pro.getProperty("no_of_units");
		String website = pro.getProperty("website");
		String category = pro.getProperty("category");
		String price = pro.getProperty("price");
		String commision = pro.getProperty("commision");
		String description = pro.getProperty("description");
		
		driver.findElement(By.name("servicename")).sendKeys(service_name);
		
		WebElement ele = driver.findElement(By.name("service_usageunit"));
		Select s = new Select(ele);
		s.selectByValue(usage_unit);
		
		driver.findElement(By.name("qty_per_unit")).sendKeys(no_of_units);
		driver.findElement(By.name("website")).sendKeys(website);
		
		ele = driver.findElement(By.name("servicecategory"));
		Select sas = new Select(ele);
		sas.selectByValue(category);
		
		driver.findElement(By.name("unit_price")).sendKeys(price);
		driver.findElement(By.name("commissionrate")).sendKeys(commision);
		driver.findElement(By.name("tax1_check")).click();
		driver.findElement(By.name("description")).sendKeys(description);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//img[@src=\"themes/images/actionGenPurchaseOrder.gif\"]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@value=\"Add Service\"]")).click();
		
		
		
		
		
		
		
		
		Thread.sleep(3000);
		driver.close();
		

	}

}
