package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibraries.WebDriverUtility;

public class CreatingNewContact extends WebDriverUtility {
	
	WebDriver driver;
	
	public CreatingNewContact(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lstName;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[@name=\"account_name\"]/following-sibling::img")
	private WebElement orgLookUpImg;
	
	/**
	 * used to create contact with mandatory information
	 * @param contactLastName
	 */
	public void createContact(String contactLastName) {
		
		lstName.sendKeys(contactLastName);
		saveButton.click();
	}
	
	public void createContact(String contactLastName, String orgName) {
		
		lstName.sendKeys(contactLastName);
		orgLookUpImg.click();
		
		switchToWindow(driver, "Accounts");
		
		Organizations op = new Organizations(driver);
		op.getSearchEdit().sendKeys(orgName);
		op.getSearchButton().click();
		
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();  //it is dynamic,therefore in this case we cannot use @findBy
		switchToWindow(driver, "Contacts");
		
		saveButton.click();
	}
	

}
