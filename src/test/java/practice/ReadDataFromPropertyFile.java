package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadDataFromPropertyFile {

	public static void main(String[] args) throws IOException, InterruptedException {
		/* step 1: get the java representation object of the physical file */
		FileInputStream fis = new FileInputStream("./data/commonData.properties");
		
		/* step 2: load all the keys using properties class */
		Properties proj = new Properties();
		
		proj.load(fis);
		
		/* read the data from file using getProperty key */
		String browser = proj.getProperty("browser");
		String url = proj.getProperty("url");
		String username = proj.getProperty("username");
		String password = proj.getProperty("password");
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		
		WebDriver driver = null;
		
		if(browser.equals("chrome"))
			driver = new ChromeDriver();
		else if(browser.equals("firefox"))
			driver = new FirefoxDriver();
		//else if(browser.equals("edge"))
			//driver = new EdgeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(10000);
		driver.close();
		
		
		

	}

}