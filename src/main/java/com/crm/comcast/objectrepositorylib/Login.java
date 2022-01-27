package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibraries.WebDriverUtility;

/**
 * 
 * @author Rahul Srivastava
 *
 */
public class Login extends WebDriverUtility {
	
	WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement userNameEdit;
	
	@FindBy(name = "user_password")
	private WebElement passwordEdit;
	
	@FindAll({@FindBy(id = "submitButton"), @FindBy(xpath = "//input[@type=\"submit\"]")})
	private WebElement loginButton;
	
	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginButton() {
		return loginButton;
	}
	
	/**
	 * Login to application
	 * @param userName
	 * @param password
	 */
	public void loginToApp(String userName, String password)
	{
		maximizeWindow(driver);
		waitForPageLoad(driver);
		
		userNameEdit.sendKeys(userName);
		passwordEdit.sendKeys(password);
		loginButton.click();
	}
	
}
