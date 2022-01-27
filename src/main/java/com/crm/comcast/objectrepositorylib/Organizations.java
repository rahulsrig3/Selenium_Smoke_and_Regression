package com.crm.comcast.objectrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizations {
	
	WebDriver driver;
	
	public Organizations(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(xpath = "//img[@title=\"Create Organization...\"]")
	private WebElement createNewOrgImg;
	
	@FindBy(name = "search_text")
	private WebElement searchEdit;
	
	@FindBy(name = "search")
	private WebElement searchButton;


	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

	public WebElement getCreateNewOrgImg() {
		return createNewOrgImg;
	}

}
