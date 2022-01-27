package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.GenericLibraries.WebDriverUtility;

public class CreatingNewOrganization extends WebDriverUtility {
	
	WebDriver driver;
	
	public CreatingNewOrganization(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;
	
	@FindBy(name = "industry")
	private WebElement indName;
	
	/**
	 * used to create new organization with mandatory info
	 * @param orgName
	 */
	public void createOrg(String orgName) {
		orgNameEdit.sendKeys(orgName); 
		saveBtn.click();
	}
	
	/**
	 * used to create new organization with mandatory information
	 * @param orgName
	 */
	public void createOrg(String orgName, String indType) {
		orgNameEdit.sendKeys(orgName); 
		select(indName, indType);
		saveBtn.click();
	}
	
}
