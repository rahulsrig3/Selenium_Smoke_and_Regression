package com.crm.ContactTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.GenericLibraries.BaseClass;
import com.crm.comcast.objectrepositorylib.ContactInformation;
import com.crm.comcast.objectrepositorylib.Contacts;
import com.crm.comcast.objectrepositorylib.CreatingNewContact;
import com.crm.comcast.objectrepositorylib.CreatingNewOrganization;
import com.crm.comcast.objectrepositorylib.Home;
import com.crm.comcast.objectrepositorylib.OrganizationInformation;
import com.crm.comcast.objectrepositorylib.Organizations;

public class ContactTest extends BaseClass {
	
	
@Test(groups = "smokeTest")

	public void createContactTest() throws Throwable, Throwable {
	
		//read test data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
	
		//navigate to contact page
		Home hm = new Home(driver);
		hm.getContactLink().click();
			
		//navigate to create new contact page
		Contacts co = new Contacts(driver);
		co.getCreateNewContactImg().click();
			
		//create contact
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.createContact(lastName);
			
		//verify the contact details
		ContactInformation ci = new ContactInformation(driver);
		String actLastName = ci.getContactHeaderSuccMsg().getText();
		
		Assert.assertTrue(actLastName.contains(lastName));
		
		/*
		if (actLastName.contains(lastName)) {
			System.out.println(lastName + "--> Contact last name is verified == Passed Script");
		}
		else
		{
			System.out.println(lastName + "--> Contact last name is not verified == Failed Script");
		}
		*/
	}
				
		
				
@Test(groups = "regressionTest")
	
	public void createContactWithOrgTest() throws Throwable, Throwable {
	
		//read test data from excel file
		String lastName = eLib.getDataFromExcel("Contact", 1, 2) + jLib.getRandomNumber();
		String orgName = eLib.getDataFromExcel("Contact", 4, 2) + jLib.getRandomNumber();
		
		//Navigate to Organization page
		Home hp = new Home(driver);
		hp.getOrgLink().click();
				
		//Navigate to create new organization page
		Organizations op = new Organizations(driver);
		op.getCreateNewOrgImg().click();
				
		//Creating new Organization
		CreatingNewOrganization cno = new CreatingNewOrganization(driver);
		cno.createOrg(orgName);
		
		//wait for header element
		OrganizationInformation oi = new OrganizationInformation(driver);
		wLib.waitForElementToVisible(driver, oi.getOrgHeaderSucMsg());
		
		//navigate to contact page
		Home hm = new Home(driver);
		hm.getContactLink().click();
		
		//navigate to create new contact page
		Contacts co = new Contacts(driver);
		co.getCreateNewContactImg().click();
		
		//create a new contact with orgName page
		CreatingNewContact cnc = new CreatingNewContact(driver);
		cnc.createContact(lastName, orgName);
		
		//verify the details
		ContactInformation ci = new ContactInformation(driver);
		String actLastName = ci.getContactHeaderSuccMsg().getText();
		
		Assert.assertTrue(actLastName.contains(lastName));
		
		/*
		if (actLastName.contains(lastName)) 
		{
			System.out.println(lastName + "- Contact last name is verified -- Passed");
		}
		else
		{
			System.out.println(lastName + "- Contact last name is not verified -- Failed");
		}    */
		
		String actOrgName = ci.getOrgNameInfo().getText();
		
		SoftAssert softA = new SoftAssert();
		softA.assertEquals(actOrgName, orgName);
		/*
		if(actOrgName.trim().equals(orgName))
			System.out.println(orgName + " is verified in contacts page - Passed");
		else
			System.out.println(orgName + " is not verified in contacts page - Failed");
		*/
	}
}
		