package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.crm.comcast.objectrepositorylib.Login;

public class Login_POM_Practice {
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		
		Login lp = new Login(driver);
		 
	    lp.loginToApp("admin", "admin");
	}

}
